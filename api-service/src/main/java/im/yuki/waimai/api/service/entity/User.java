package im.yuki.waimai.api.service.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author longkun
 * @version V1.0.0
 * @date 2022/5/8 9:57 PM
 * @description 用户实体类
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 7023680386768508926L;

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

}
