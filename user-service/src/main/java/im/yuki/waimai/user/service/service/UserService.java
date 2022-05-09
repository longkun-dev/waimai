package im.yuki.waimai.user.service.service;

import im.yuki.waimai.user.service.entity.User;

import java.util.List;

public interface UserService {

    String doLogin(String username, String password);

    List<User> listUserByPage(int pageNum, int pageSize);

}
