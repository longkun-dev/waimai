package im.yuki.waimai.user.service.controller;

import im.yuki.waimai.user.service.entity.User;
import im.yuki.waimai.user.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/findByPage")
    public List<User> findByPage(@RequestParam int pageNum, @RequestParam int pageSize) {
        return userService.listUserByPage(pageNum, pageSize);
    }

    @GetMapping("/doLogin")
    public String doLogin(@RequestParam String username, @RequestParam String password) {
        return userService.doLogin(username, password);
    }
}
