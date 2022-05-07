package im.yuki.waimai.menu.service.controller;

import im.yuki.waimai.menu.service.entity.Menu;
import im.yuki.waimai.menu.service.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/findByPage")
    public List<Menu> findByPage(int pageNum, int pageSize) {
        return menuService.findByPage(pageNum, pageSize);
    }
}
