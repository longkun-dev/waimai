package im.yuki.waimai.common.service.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {

    private String createdBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    private String updatedBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;
}
