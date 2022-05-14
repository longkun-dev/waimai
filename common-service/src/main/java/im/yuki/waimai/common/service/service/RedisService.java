package im.yuki.waimai.common.service.service;

/**
 * @author longkun
 * @desc Redis操作工具类
 * @since 5/14/2022 1:05 AM
 */
public interface RedisService {

    void set(String key, String value, int expSeconds);

    String get(String key);

    void del(String key);
}
