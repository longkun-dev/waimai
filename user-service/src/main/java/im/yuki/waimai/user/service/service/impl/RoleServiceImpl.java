package im.yuki.waimai.user.service.service.impl;

import im.yuki.waimai.user.service.dao.RoleDao;
import im.yuki.waimai.user.service.entity.Role;
import im.yuki.waimai.user.service.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author longkun
 * @desc 角色逻辑处理接口
 * @since 5/14/2022 3:05 PM
 */
@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findALlRoles() {
        return roleDao.findAll();
    }

    @Override
    public String updateRoleName(Role role) {
        String roleCode = role.getRoleCode();
        String roleName = role.getRoleName();
        if (StringUtils.isBlank(roleCode) || StringUtils.isBlank(roleName)) {
            return "角色编码和角色名称不能为空";
        }

        Integer result = roleDao.updateRoleName(roleCode, roleName);

        return result == null ? "不存在该角色信息" : "角色名称更新成功";
    }
}
