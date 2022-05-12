package im.yuki.waimai.user.service.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import im.yuki.waimai.common.service.util.JwtUtil;
import im.yuki.waimai.common.service.util.RequestUtil;
import im.yuki.waimai.user.service.dao.UserDao;
import im.yuki.waimai.user.service.entity.User;
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

    @Override
    public Map<String, Object> doLogin(String username, String password) {
        Map<String, Object> resultMap = new HashMap<>();

        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            resultMap.put("result", "验证失败");
            resultMap.put("message", "用户名和密码不能为空");
            return resultMap;
        }

        User user = userDao.findByUsernameAndPassword(username, password);
        if (user != null) {
            String token = JwtUtil.createToken(UUID.randomUUID().toString(), username, null);
            resultMap.put("result", "验证成功");
            resultMap.put("message", "验证成功");
            resultMap.put("token", token);
        } else {
            resultMap.put("result", "验证失败");
            resultMap.put("message", "用户名或密码错误");
        }
        return resultMap;
    }

    @Override
    public List<User> listUserByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> allUserList = userDao.findAll();
        PageInfo<User> pageInfo = new PageInfo<>(allUserList);
        return pageInfo.getList();
    }

    @Override
    public User getCurrentUserInfo() {
        String username = RequestUtil.getCurrentUsername();
        if (StringUtils.isBlank(username)) {
            return null;
        }
        User user = userDao.findByUsername(username);
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
            user = userDao.findByUsername(subject);
        } catch (Exception e) {
            log.warn("token 解析失败，{}", token);
            log.warn("异常信息: ", e);
            return null;
        }
        return user;
    }
}
