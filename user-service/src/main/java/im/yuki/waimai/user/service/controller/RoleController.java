package im.yuki.waimai.user.service.controller;

import im.yuki.waimai.common.service.entity.Response;
import im.yuki.waimai.common.service.util.ResponseUtil;
import im.yuki.waimai.user.service.entity.Role;
import im.yuki.waimai.user.service.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author longkun
 * @desc 用户角色接口
 * @since 5/14/2022 3:11 PM
 */
@RestController
@RequestMapping("/user/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/findAll")
    public Response<List<Role>> findAll() {
        return ResponseUtil.success(roleService.findALlRoles());
    }

    @PutMapping("/")
    public Response<String> updateRoleName(@RequestBody Role role) {
        return ResponseUtil.successWithMessage(roleService.updateRoleName(role));
    }

}
