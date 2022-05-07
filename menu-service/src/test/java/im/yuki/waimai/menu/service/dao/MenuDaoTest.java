package im.yuki.waimai.menu.service.dao;

import im.yuki.waimai.menu.service.entity.Menu;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class MenuDaoTest {

    @Autowired
    private MenuDao menuDao;

    @Test
    public void findAll() {
        List<Menu> menuList = menuDao.findAll();
        log.info("menuList: {}", menuList);
    }

    @Test
    public void count() {
        Integer count = menuDao.count();
        log.info("count: {}", count);
    }
}
