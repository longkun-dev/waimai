package im.yuki.waimai.user.service.controller;

import im.yuki.waimai.common.service.entity.Response;
import im.yuki.waimai.common.service.util.ResponseUtil;
import im.yuki.waimai.user.service.entity.AccountStatus;
import im.yuki.waimai.user.service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author longkun
 * @desc
 * @since 5/14/2022 3:38 PM
 */
@RestController
@RequestMapping("/user/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/findAll")
    public Response<List<AccountStatus>> findAll() {
        return ResponseUtil.success(accountService.findAll());
    }

    @PutMapping("/")
    public Response<String> updateStatusName(@RequestBody AccountStatus accountStatus) {
        return ResponseUtil.successWithMessage(accountService.updateAccountStatusName(accountStatus));
    }
}
