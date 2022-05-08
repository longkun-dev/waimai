package im.yuki.waimai.api.service.utils;

import im.yuki.waimai.api.service.entity.ResponseEntity;
import im.yuki.waimai.api.service.entity.ResultEnum;

public class ResponseUtil {

    public static <T> ResponseEntity<T> success() {
        return new ResponseEntity<>(ResultEnum.SUCCESS);
    }

    public static <T> ResponseEntity<T> success(T data) {
        return new ResponseEntity<>(ResultEnum.SUCCESS, data);
    }

    public static <T> ResponseEntity<T> failed() {
        return new ResponseEntity<>(ResultEnum.FAILED);
    }

}
