package im.yuki.waimai.user.service.entity;

import lombok.Getter;

/**
 * @author longkun
 * @version V1.0.0
 * @date 2022/5/8 10:07 PM
 * @description 角色信息
 */
@Getter
public enum RoleEnum {

    ROLE_VIEW("ROLE_VIEW", "查看"),
    ROLE_EDIT("ROLE_EDIT", "编辑"),
    ROLE_DEL("ROLE_DEL", "删除");

    private final String roleCode;

    private final String roleName;

    // 私有构造方法
    RoleEnum(String roleCode, String roleName) {
        this.roleCode = roleCode;
        this.roleName = roleName;
    }
}
