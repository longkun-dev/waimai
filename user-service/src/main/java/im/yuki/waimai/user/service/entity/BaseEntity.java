package im.yuki.waimai.user.service.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author longkun
 * @version V1.0.0
 * @date 2022/5/8 9:59 PM
 * @description 实体类基础字段
 */
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -1478896162619741246L;

    private String createdBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    private String updatedBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;

}
