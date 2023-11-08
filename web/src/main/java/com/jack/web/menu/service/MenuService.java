package com.jack.web.menu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jack.web.menu.entity.Menu;

import java.util.List;

public interface MenuService extends IService<Menu> {

    List<Menu> getList();

    List<Menu> getParentList();

    List<Menu> getMenuByUserId(Long userId);

    //根据业主id查权限
    List<Menu> getMenuByUserIdForLiveUser(Long userId);

    List<Menu> getMenuByRoleId(Long roleId);
}
