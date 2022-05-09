package im.yuki.waimai.user.service.dao;

import im.yuki.waimai.user.service.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author longkun
 * @version V1.0.0
 * @date 2022/5/8 10:11 PM
 * @description 用户数据库操作接口
 */
@Mapper
public interface UserDao {

    int save(User user);

    int count();

    int deleteById(int id);

    List<User> findAll();

    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}
