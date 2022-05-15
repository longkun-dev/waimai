package im.yuki.waimai.user.service.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import im.yuki.waimai.common.service.exception.ParamErrorException;
import im.yuki.waimai.common.service.util.JwtUtil;
import im.yuki.waimai.common.service.util.RequestUtil;
import im.yuki.waimai.user.service.constant.ConstantVal;
import im.yuki.waimai.user.service.dao.UserDao;
import im.yuki.waimai.user.service.entity.User;
import im.yuki.waimai.user.service.feign.RedisClient;
import im.yuki.waimai.user.service.service.UserService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisClient redisClient;

    @Override
    public Map<String, Object> doLogin(String uid, String password) {
        Map<String, Object> resultMap = new HashMap<>();

        if (StringUtils.isBlank(uid) || StringUtils.isBlank(password)) {
            throw new ParamErrorException("用户 id 和密码不能为空");
        }

        User user = userDao.findByUidAndPassword(uid, password);
        if (user != null) {
            String token = JwtUtil.createToken(UUID.randomUUID().toString(), uid, null);
            // redis 超时时间和 token 默认时间保持一致
            redisClient.set(ConstantVal.TOKEN_PREFIX + uid, token, JwtUtil.JWT_TTL.intValue());
            resultMap.put("token", token);
            log.info("【用户登录】用户 {}(id: {}) 成功登录系统", user.getUsername(), uid);
        } else {
            throw new ParamErrorException("用户名或密码错误");
        }

        return resultMap;
    }

    @Override
    public void doLogout() {
        String currentUid = RequestUtil.getCurrentUid();
        String tokenKey = ConstantVal.TOKEN_PREFIX + currentUid;
        redisClient.del(tokenKey);

        log.info("【用户退出】用户 {} 退出登录", currentUid);
    }

    @Override
    public PageInfo<User> listUserByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> allUserList = userDao.findAll();
        return new PageInfo<>(allUserList);
    }

    @Override
    public User getCurrentUserInfo() {
        String uid = RequestUtil.getCurrentUid();
        if (StringUtils.isBlank(uid)) {
            return null;
        }
        User user = userDao.findByUid(uid);
        if (StringUtils.isNotBlank(user.getPassword())) {
            // 屏蔽用户密码
            user.setPassword("****");
        }
        return user;
    }

    @Override
    public User findByToken(String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        }

        User user;
        try {
            Claims claims = JwtUtil.parseToken(token);
            String subject = claims.getSubject();
            user = userDao.findByUid(subject);
        } catch (Exception e) {
            log.warn("token 解析失败，{}", token);
            log.warn("异常信息: ", e);
            return null;
        }
        return user;
    }

    @Override
    public PageInfo<User> findByNameOrRole(String username, String roleCode, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userDao.findByUsernameOrRoleCode(username, roleCode);
        return new PageInfo<>(userList);
    }

    @Override
    public String updateAccountStatus(String uid, String newAccountStatus) {
        Integer result = userDao.updateAccountStatus(uid, newAccountStatus);
        return result == null ? "不存在该用户" : "账户状态更新成功";
    }

    @Override
    public String updateUserInfo(User user) {
        if (user == null || StringUtils.isBlank(user.getUsername())) {
            return "uid不能为空";
        }

        userDao.update(user);

        return "更新成功";
    }
}

