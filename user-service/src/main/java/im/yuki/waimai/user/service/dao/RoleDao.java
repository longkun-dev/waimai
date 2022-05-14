package im.yuki.waimai.user.service.dao;

import im.yuki.waimai.user.service.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author longkun
 * @desc
 * @since 5/14/2022 3:06 PM
 */
@Mapper
public interface RoleDao {

    Integer save();

    List<Role> findAll();

    Integer updateRoleName(@Param("roleCode") String roleCode, @Param("roleName") String roleName);
}
