package im.yuki.waimai.menu.service.service;

import im.yuki.waimai.menu.service.entity.Menu;

import java.util.List;

public interface MenuService {

    /**
     * 查询所有的菜品
     *
     * @return 返回所有的菜品列表
     */
    List<Menu> findByPage(int pageNum, int pageSize);
}
