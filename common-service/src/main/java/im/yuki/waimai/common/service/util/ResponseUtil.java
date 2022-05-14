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

    public static <T> Response<T> successWithMessage(String message) {
        return new Response<>(ResultEnum.SUCCESS, message);
    }

    public static <T> Response<T> failed(String message) {
        return new Response<>(ResultEnum.FAILED.getCode(), message);
    }

}
