package com.itheima.health.dao;

import com.itheima.health.pojo.CheckGroup;
import com.itheima.health.pojo.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MenuDao {
    List<Menu> checkAllMenu(Integer id);
}
