package im.yuki.waimai.user.service.service.impl;

import im.yuki.waimai.user.service.dao.AccountStatusDao;
import im.yuki.waimai.user.service.entity.AccountStatus;
import im.yuki.waimai.user.service.entity.User;
import im.yuki.waimai.user.service.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author longkun
 * @desc 账户状态更新
 * @since 5/14/2022 3:25 PM
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountStatusDao accountStatusDao;

    @Override
    public List<AccountStatus> findAll() {
        return accountStatusDao.findAll();
    }

    @Override
    public String updateAccountStatusName(AccountStatus accountStatus) {
        String code = accountStatus.getAccountStatusCode();
        String name = accountStatus.getAccountStatusName();
        if (StringUtils.isBlank(code) || StringUtils.isBlank(name)) {
            return "编码和名称不能为空";
        }
        Integer result = accountStatusDao.updateStatusName(code, name);
        return result == null ? "数据不存在" : "状态名称更新成功";
    }
}
