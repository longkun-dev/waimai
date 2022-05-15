package im.yuki.waimai.common.service.entity;

import lombok.Getter;

/**
 * @author longkun
 * @desc 自定义异常枚举
 * @since 5/14/2022 10:17 PM
 */
@Getter
public enum ExceptionEnum {

    PARAM_ERROR(400, "请求参数错误"),
    AUTH_ERROR(403, "无权执行此操作"),
    GATEWAY_ERROR(502, "网关异常"),
    SERVICE_UNAVAILABLE(503, "服务不可用");

    private final Integer code;

    private final String message;

    ExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
