package im.yuki.waimai.user.service.dao;

import im.yuki.waimai.user.service.entity.AccountStatus;
import im.yuki.waimai.user.service.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author longkun
 * @desc
 * @since 5/14/2022 3:26 PM
 */
@Mapper
public interface AccountStatusDao {

    Integer save(AccountStatus accountStatus);

    List<AccountStatus> findAll();

    Integer updateStatusName(@Param("statusCode") String statusCode, @Param("newStatusName") String newStatusName);
}
