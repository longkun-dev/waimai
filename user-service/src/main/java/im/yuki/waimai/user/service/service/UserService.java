package im.yuki.waimai.user.service.service;

import im.yuki.waimai.user.service.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    Map<String, Object> doLogin(String username, String password);

    List<User> listUserByPage(int pageNum, int pageSize);

    User getCurrentUserInfo();

    User findByToken(String token);
}
