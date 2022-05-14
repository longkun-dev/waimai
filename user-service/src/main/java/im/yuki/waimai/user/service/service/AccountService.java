package im.yuki.waimai.user.service.service;

import im.yuki.waimai.user.service.entity.AccountStatus;
import im.yuki.waimai.user.service.entity.User;

import java.util.List;

/**
 * @author longkun
 * @desc 账户状态接口
 * @since 5/14/2022 3:21 PM
 */
public interface AccountService {

    /**
     * 查询所有的账户状态
     *
     * @return 所有的账户状态
     */
    List<AccountStatus> findAll();

    /**
     * 更新状态名称
     *
     * @param accountStatus 账户状态信息
     * @return 成功或失败提示
     */
    String updateAccountStatusName(AccountStatus accountStatus);

}
