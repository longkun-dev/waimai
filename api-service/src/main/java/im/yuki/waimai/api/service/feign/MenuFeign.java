package im.yuki.waimai.api.service.feign;

import im.yuki.waimai.api.service.entity.Menu;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "menu-service")
public interface MenuFeign {

    @GetMapping(value = "/menu/findByPage")
    List<Menu> findByPage(@RequestParam int pageNum, @RequestParam int pageSize);

}
