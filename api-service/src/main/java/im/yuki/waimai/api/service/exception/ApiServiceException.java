package im.yuki.waimai.api.service.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ApiServiceException extends RuntimeException {

    private static final long serialVersionUID = 6879655091125751638L;

    ApiServiceException(String message) {
        super(message);
    }
}
