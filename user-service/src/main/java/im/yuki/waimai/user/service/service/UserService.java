package im.yuki.waimai.user.service.service;

import com.github.pagehelper.PageInfo;
import im.yuki.waimai.user.service.entity.User;

import java.util.Map;

public interface UserService {

    Map<String, Object> doLogin(String id, String password);

    void doLogout();

    PageInfo<User> listUserByPage(int pageNum, int pageSize);

    User getCurrentUserInfo();

    User findByToken(String token);

    PageInfo<User> findByNameOrRole(String username, String roleCode, int pageNum, int pageSize);

    String updateAccountStatus(String uid, String newAccountStatus);

    String updateUserInfo(User user);
}
