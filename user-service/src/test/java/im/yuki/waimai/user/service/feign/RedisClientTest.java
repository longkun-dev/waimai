package im.yuki.waimai.user.service.feign;

import im.yuki.waimai.user.service.UserServiceApplication;
import im.yuki.waimai.user.service.constant.ConstantVal;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author longkun
 * @desc
 * @since 5/14/2022 9:38 AM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserServiceApplication.class)
@Slf4j
public class RedisClientTest {

    private final String username = "songaimei";

    @Autowired
    private RedisClient redisClient;

    @Test
    public void set() {
        // 5 分钟
        redisClient.set(ConstantVal.TOKEN_PREFIX + username, "abcd", 60 * 5);
    }

    @Test
    public void get() {
        String token = redisClient.get(ConstantVal.TOKEN_PREFIX + username);
        log.info("token: {}", token);
    }

    @Test
    public void del() {
        redisClient.del(ConstantVal.TOKEN_PREFIX + username);
        String token = redisClient.get(ConstantVal.TOKEN_PREFIX + username);
        log.info("删除 token  后再获取结果: {}", token);
    }
}