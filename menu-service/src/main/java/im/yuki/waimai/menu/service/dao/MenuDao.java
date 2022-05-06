package im.yuki.waimai.menu.service.dao;

import im.yuki.waimai.menu.service.entity.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDao {

    List<Menu> findAll();

    int count();

    Menu findById(long id);

    int save(Menu menu);

    int update(Menu menu);

    int deleteById(long id);
}
