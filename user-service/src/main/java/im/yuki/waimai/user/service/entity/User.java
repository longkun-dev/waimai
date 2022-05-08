package im.yuki.waimai.user.service.entity;

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

    private Integer id;

    private String username;

    private String password;

    private String roleCode;

}
