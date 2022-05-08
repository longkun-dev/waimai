package im.yuki.waimai.user.service.dao;

import im.yuki.waimai.user.service.entity.User;
import org.apache.ibatis.annotations.Mapper;

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

    int update(User user);

    int deleteById(int id);

}
