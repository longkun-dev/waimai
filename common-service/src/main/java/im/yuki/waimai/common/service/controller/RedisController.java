package im.yuki.waimai.common.service.controller;

import im.yuki.waimai.common.service.entity.Response;
import im.yuki.waimai.common.service.service.RedisService;
import im.yuki.waimai.common.service.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author longkun
 * @desc
 * @since 5/14/2022 1:12 AM
 */
@RestController
@RequestMapping("/common")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @GetMapping("/redis/{key}")
    public Response<String> get(@PathVariable String key) {
        return ResponseUtil.success(redisService.get(key));
    }

    @DeleteMapping("/redis/{key}")
    public Response<String> del(@PathVariable String key) {
        redisService.del(key);
        return ResponseUtil.success();
    }

    @PutMapping("/redis/{key}/{value}/{ttl}")
    public Response<String> set(@PathVariable String key, @PathVariable String value, @PathVariable Integer ttl) {
        redisService.set(key, value, ttl);
        return ResponseUtil.success();
    }
}
