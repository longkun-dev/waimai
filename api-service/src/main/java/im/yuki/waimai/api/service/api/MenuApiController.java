package im.yuki.waimai.api.service.api;

import im.yuki.waimai.api.service.entity.Menu;
import im.yuki.waimai.api.service.entity.ResponseEntity;
import im.yuki.waimai.api.service.feign.MenuFeign;
import im.yuki.waimai.api.service.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/waimai/api/menu")
public class MenuApiController {

    @Autowired
    private MenuFeign menuFeign;

    @GetMapping("/findByPage")
    public ResponseEntity<List<Menu>> findByPage(@RequestParam int pageNum, @RequestParam int pageSize) {
        return ResponseUtil.success(menuFeign.findByPage(pageNum, pageSize));
    }
}
