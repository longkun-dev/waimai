package im.yuki.waimai.api.service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEntity<T> implements Serializable {

    private static final long serialVersionUID = -5129073171481315084L;

    private int code;

    private String message;

    private T data;

    public ResponseEntity(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseEntity(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
    }

    public ResponseEntity(ResultEnum resultEnum, T data) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
        this.data = data;
    }
}
