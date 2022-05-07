package im.yuki.waimai.menu.service.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import im.yuki.waimai.menu.service.dao.MenuDao;
import im.yuki.waimai.menu.service.entity.Menu;
import im.yuki.waimai.menu.service.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public List<Menu> findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Menu> menuList = menuDao.findAll();
        PageInfo<Menu> pageInfo = new PageInfo<>(menuList);
        return pageInfo.getList();
    }
}
