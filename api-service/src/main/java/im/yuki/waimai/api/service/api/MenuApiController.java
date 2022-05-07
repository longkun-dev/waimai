package im.yuki.waimai.api.service.api;

import im.yuki.waimai.api.service.entity.Menu;
import im.yuki.waimai.api.service.feign.MenuFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/waimai/menu/api")
public class MenuApiController {

    @Autowired
    private MenuFeign menuFeign;

    @GetMapping("/findByPage")
    public List<Menu> findByPage(int pageNum, int pageSize) {
        return menuFeign.findByPage(pageNum, pageSize);
    }
}
