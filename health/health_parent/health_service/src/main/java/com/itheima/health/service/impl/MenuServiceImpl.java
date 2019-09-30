package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.health.dao.CheckGroupDao;
import com.itheima.health.dao.MenuDao;
import com.itheima.health.dao.UserDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.pojo.CheckGroup;
import com.itheima.health.pojo.Menu;
import com.itheima.health.pojo.User;
import com.itheima.health.service.CheckGroupService;
import com.itheima.health.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CheckItemServiceImpl
 * @Description TODO
 * @Author ly
 * @Company 深圳黑马程序员
 * @Date 2019/9/11 9:55
 * @Version V1.0
 */
@Service(interfaceClass = MenuService.class)
@Transactional
public class MenuServiceImpl implements MenuService {
    @Autowired
    UserDao userDao;
    @Autowired
    MenuDao menuDao;

    @Override
    public List<Menu> CheckMenu(String username) {
        User user = userDao.findUserByUsername(username);
        Integer id = user.getId();
        List<Menu> listAll = menuDao.checkAllMenu(id);
        ArrayList<Menu> listParent = new ArrayList<>();
        ArrayList<Menu> listChild = new ArrayList<>();
        for (Menu menu : listAll) {
            Integer parentMenuId = menu.getParentMenuId();
            if (parentMenuId != null) {//为子
                listChild.add(menu);
            } else {//为父
                Menu parenMenu = new Menu();
                parenMenu.setId(menu.getId());
                parenMenu.setPath(menu.getPath());
                parenMenu.setName(menu.getName());
                parenMenu.setIcon(menu.getIcon());
                parenMenu.setChildren(new ArrayList<Menu>());
                listParent.add(parenMenu);
            }
        }
        for (Menu pMenu : listParent) {
            for (Menu cMenu : listChild) {
                Integer parentMenuId = cMenu.getParentMenuId();
                Integer i = pMenu.getId();
                if (parentMenuId == i) {
                    pMenu.getChildren().add(cMenu);
                }
            }
        }
        return listParent;
    }
}
