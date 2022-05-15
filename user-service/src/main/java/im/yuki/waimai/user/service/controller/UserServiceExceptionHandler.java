package im.yuki.waimai.user.service.controller;

import im.yuki.waimai.common.service.entity.Response;
import im.yuki.waimai.common.service.exception.AuthException;
import im.yuki.waimai.common.service.exception.ParamErrorException;
import im.yuki.waimai.common.service.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author longkun
 * @desc user 服务统一异常处理
 * @since 5/15/2022 12:42 AM
 */
@RestControllerAdvice
@Slf4j
public class UserServiceExceptionHandler {

    @ExceptionHandler(ParamErrorException.class)
    public Response<?> handlerParamException(ParamErrorException paramErrorException) {
        return ResponseUtil.failed(paramErrorException);
    }

    @ExceptionHandler(AuthException.class)
    public Response<?> handlerParamException(AuthException authException) {
        return ResponseUtil.failed(authException);
    }


}
