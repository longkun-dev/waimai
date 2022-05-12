package im.yuki.waimai.user.service.controller;

import im.yuki.waimai.common.service.entity.Response;
import im.yuki.waimai.common.service.util.ResponseUtil;
import im.yuki.waimai.user.service.entity.User;
import im.yuki.waimai.user.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/findByPage")
    public Response<List<User>> findByPage(@RequestParam int pageNum, @RequestParam int pageSize) {
        return ResponseUtil.success(userService.listUserByPage(pageNum, pageSize));
    }

    @GetMapping("/doLogin")
    public Response<Map<String, Object>> doLogin(@RequestParam String username, @RequestParam String password) {
        return ResponseUtil.success(userService.doLogin(username, password));
    }

    @GetMapping("/currentUserInfo")
    public Response<User> getCurrentUserInfo() {
        return ResponseUtil.success(userService.getCurrentUserInfo());
    }

    @GetMapping("/findByToken")
    public Response<User> findByToken(@RequestParam String token) {
        return ResponseUtil.success(userService.findByToken(token));
    }
}
