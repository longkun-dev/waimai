package im.yuki.waimai.user.service.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import im.yuki.waimai.user.service.dao.UserDao;
import im.yuki.waimai.user.service.entity.User;
import im.yuki.waimai.user.service.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public String doLogin(String username, String password) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return "用户名和密码不能为空";
        }

        User user = userDao.findByUsernameAndPassword(username, password);
        return user != null ? "success" : "用户名或密码错误";
    }

    @Override
    public List<User> listUserByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> allUserList = userDao.findAll();
        PageInfo<User> pageInfo = new PageInfo<>(allUserList);
        return pageInfo.getList();
    }
}
