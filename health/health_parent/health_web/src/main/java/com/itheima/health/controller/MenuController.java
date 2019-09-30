package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constants.MessageConstant;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.CheckGroup;
import com.itheima.health.pojo.Menu;
import com.itheima.health.service.CheckGroupService;
import com.itheima.health.service.MenuService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName CheckItemController
 * @Description TODO
 * @Author ly
 * @Company 深圳黑马程序员
 * @Date 2019/9/11 9:57
 * @Version V1.0
 */
@RestController // json响应
@RequestMapping(value = "/menu")
public class MenuController {
    @Reference
    MenuService menuService;

    @RequestMapping(value = "/CheckMenu")
    public List<Menu> CheckMenu(String username) {
        List<Menu> list = menuService.CheckMenu(username);
        return list;
    }

}
