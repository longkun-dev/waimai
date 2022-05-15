package im.yuki.waimai.common.service.exception;

import im.yuki.waimai.common.service.entity.ExceptionEnum;

/**
 * @author longkun
 * @desc 参数错误异常
 * @since 5/14/2022 9:57 PM
 */
public class ParamErrorException extends BaseException {

    private static final long serialVersionUID = 7185827229469063674L;

    public ParamErrorException(String message) {
        super(message);
        super.code = ExceptionEnum.PARAM_ERROR.getCode();
    }

}
