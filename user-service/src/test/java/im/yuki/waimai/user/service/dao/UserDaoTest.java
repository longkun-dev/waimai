package im.yuki.waimai.user.service.dao;

import im.yuki.waimai.user.service.UserServiceApplication;
import im.yuki.waimai.user.service.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserServiceApplication.class)
@Slf4j
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testCount() {
        Integer count = userDao.count();
        log.info("count: {}", count);
    }

    @Test
    public void testFindAll() {
        List<User> all = userDao.findAll();
        log.info("allUser: {}", all);
    }
}