package im.yuki.waimai.user.service.service;

import im.yuki.waimai.user.service.entity.Role;

import java.util.List;

/**
 * @author longkun
 * @desc 用户角色接口
 * @since 5/14/2022 3:05 PM
 */
public interface RoleService {

    List<Role> findALlRoles();

    String updateRoleName(Role role);
}
