package im.yuki.waimai.api.service.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class Menu extends BaseEntity {

    private long id;

    private String name;

    private BigDecimal price;

}
