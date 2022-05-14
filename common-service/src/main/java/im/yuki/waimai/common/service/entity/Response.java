package im.yuki.waimai.common.service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> implements Serializable {

    private static final long serialVersionUID = -5129073171481315084L;

    private int code;

    private String message;

    private T data;

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Response(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
    }

    public Response(ResultEnum resultEnum, T data) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
        this.data = data;
    }

    public Response(ResultEnum resultEnum, String message) {
        this.code = resultEnum.getCode();
        this.message = message;
    }
}
