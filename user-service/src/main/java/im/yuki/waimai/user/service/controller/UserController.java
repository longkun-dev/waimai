package im.yuki.waimai.user.service.controller;

import com.github.pagehelper.PageInfo;
import im.yuki.waimai.common.service.entity.Response;
import im.yuki.waimai.common.service.util.ResponseUtil;
import im.yuki.waimai.user.service.entity.User;
import im.yuki.waimai.user.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/findByPage")
    public Response<PageInfo<User>> findByPage(@RequestParam int pageNum, @RequestParam int pageSize) {
        return ResponseUtil.success(userService.listUserByPage(pageNum, pageSize));
    }

    @GetMapping("/doLogin")
    public Response<Map<String, Object>> doLogin(@RequestParam String uid, @RequestParam String password) {
        return ResponseUtil.success(userService.doLogin(uid, password));
    }

    @GetMapping("/doLogout")
    public Response<?> doLogout() {
        userService.doLogout();
        return ResponseUtil.success();
    }

    @GetMapping("/currentUserInfo")
    public Response<User> getCurrentUserInfo() {
        return ResponseUtil.success(userService.getCurrentUserInfo());
    }

    @GetMapping("/findByToken")
    public Response<User> findByToken(@RequestParam String token) {
        return ResponseUtil.success(userService.findByToken(token));
    }

    @GetMapping("/findByNameOrRole")
    public Response<PageInfo<User>> findByNameOrRole(@RequestParam(required = false) String username,
                                                     @RequestParam(required = false) String roleCode,
                                                     @RequestParam int pageNum,
                                                     @RequestParam int pageSize) {
        return ResponseUtil.success(userService.findByNameOrRole(username, roleCode, pageNum, pageSize));
    }

    @PutMapping("/updateAccountStatus")
    public Response<String> updateAccountStatus(@RequestParam String uid,
                                                @RequestParam String newAccountStatus) {
        return ResponseUtil.successWithMessage(userService.updateAccountStatus(uid, newAccountStatus));
    }

    @PutMapping("/")
    public Response<String> updateUserInfo(@RequestBody User user) {
        return ResponseUtil.successWithMessage(userService.updateUserInfo(user));
    }
}
