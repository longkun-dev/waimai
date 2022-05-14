package im.yuki.waimai.common.service.config;

import im.yuki.waimai.common.service.CommonServiceApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author longkun
 * @since  5/14/2022 12:27 AM
 * @desc   ApplicationContextHolder 测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CommonServiceApplication.class)
@Slf4j
public class ApplicationContextHolderTest {

    @Test
    public void getBean() {
        ApplicationContextHolder.getBean("redisTemplate");
    }
}