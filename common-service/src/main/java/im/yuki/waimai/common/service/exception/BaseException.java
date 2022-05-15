package im.yuki.waimai.common.service.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author longkun
 * @desc
 * @since 5/15/2022 12:36 AM
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 3671244860261153233L;

    protected Integer code;

    protected String message;

    public BaseException(String message) {
        super(message);
        this.message = message;
    }

}
