package im.yuki.waimai.common.service.exception;

import im.yuki.waimai.common.service.entity.ExceptionEnum;

/**
 * @author longkun
 * @desc 鉴权失败异常
 * @since 5/14/2022 10:10 PM
 */
public class AuthException extends BaseException {

    private static final long serialVersionUID = 3561061783317330111L;

    public AuthException(String message) {
        super(message);
        super.code = ExceptionEnum.AUTH_ERROR.getCode();
    }

}
