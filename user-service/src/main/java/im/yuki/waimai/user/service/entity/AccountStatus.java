package im.yuki.waimai.user.service.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author longkun
 * @desc 账户状态
 * @since 5/14/2022 3:21 PM
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AccountStatus extends BaseEntity {

    private static final long serialVersionUID = -5587886607433251238L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 状态code
     */
    private String accountStatusCode;

    /**
     * 状态名称
     */
    private String accountStatusName;

}
