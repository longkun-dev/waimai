package im.yuki.waimai.user.service.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author longkun
 * @version V1.0.0
 * @date 2022/5/8 10:01 PM
 * @description 角色信息类
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Role extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 8456965135350530648L;

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

}
