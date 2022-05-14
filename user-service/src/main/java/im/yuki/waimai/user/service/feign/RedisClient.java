package im.yuki.waimai.user.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * @author longkun
 * @desc 调用 redis 服务
 * @since 5/14/2022 9:29 AM
 */
@Service
@FeignClient(value = "common-service")
public interface RedisClient {

    /**
     * redis set
     *
     * @param key        key
     * @param value      value
     * @param expSeconds 超时时间，单位为秒
     */
    @PutMapping("/common/redis/{key}/{value}/{expSeconds}")
    void set(@PathVariable String key, @PathVariable String value, @PathVariable int expSeconds);

    /**
     * 获取 key 对应的 value
     *
     * @param key key
     * @return key 对应的value
     */
    @GetMapping("/common/redis/{key}")
    String get(@PathVariable String key);

    /**
     * 删除 redis 中的 key
     *
     * @param key key
     * @return key 对应啊的 value
     */
    @DeleteMapping("/common/redis/{key}")
    void del(@PathVariable String key);

}
