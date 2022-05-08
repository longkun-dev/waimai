package im.yuki.waimai.api.service.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultEnum {

    SUCCESS(200, "处理成功"),
    FAILED(500, "系统错误"),
    PARAM_ERROR(400, "请求参数错误");

    private final int code;

    private final String message;

}
