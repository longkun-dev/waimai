package im.yuki.waimai.common.service.util;

import im.yuki.waimai.common.service.CommonServiceApplication;
import im.yuki.waimai.common.service.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author longkun
 * @desc   redis操作工具测试类
 * @since  5/13/2022 11:46 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CommonServiceApplication.class)
@Slf4j
public class RedisUtilTest {

    @Autowired
    private RedisService redisService;

    @Test
    public void set() {
        redisService.set("username", "songaimei", 60 * 60);
    }

    @Test
    public void get() {
        String username = redisService.get("username");
        assertEquals(username, "songaimei");
        log.info("username: {}", username);
    }

    @Test
    public void del() {
        redisService.del("songaimei");
        String username = redisService.get("username");
        log.info("exist: {}", StringUtils.isBlank(username));
    }
}