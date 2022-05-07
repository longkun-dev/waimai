package im.yuki.waimai.menu.service.dao;

import im.yuki.waimai.menu.service.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuDao {

    List<Menu> findAll();

    Integer count();

    Menu findById(long id);

    int save(Menu menu);

    int updateById(Menu menu);

    int deleteById(long id);

}
