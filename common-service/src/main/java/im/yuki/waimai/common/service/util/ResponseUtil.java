package im.yuki.waimai.common.service.util;

import im.yuki.waimai.common.service.entity.Response;
import im.yuki.waimai.common.service.entity.ResultEnum;

public class ResponseUtil {

    public static <T> Response<T> success() {
        return new Response<>(ResultEnum.SUCCESS);
    }

    public static <T> Response<T> success(T data) {
        return new Response<>(ResultEnum.SUCCESS, data);
    }

    public static <T> Response<T> success(String message) {
        return new Response<T>(ResultEnum.SUCCESS.getCode(), message);
    }

    public static <T> Response<T> failed() {
        return new Response<>(ResultEnum.FAILED);
    }

}
