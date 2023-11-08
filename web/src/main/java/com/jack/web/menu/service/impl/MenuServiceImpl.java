package com.jack.web.menu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jack.web.menu.entity.MakeMenuTree;
import com.jack.web.menu.entity.Menu;
import com.jack.web.menu.mapper.MenuMapper;
import com.jack.web.menu.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Override
    public List<Menu> getList() {

        QueryWrapper<Menu> query = new QueryWrapper<>();
        query.lambda().orderByAsc(Menu::getName).orderByAsc(Menu::getOrderNum);
        List<Menu> menus = this.baseMapper.selectList(query);
        List<Menu> menus1 = MakeMenuTree.makeTree(menus, 0L);
        return menus1;
    }

    @Override
    public List<Menu> getParentList() {
        String[] types = {"0", "1"};
        QueryWrapper<Menu> query = new QueryWrapper<>();
        query.lambda().in(Menu::getType, types).orderByAsc(Menu::getOrderNum);
        List<Menu> menus = this.baseMapper.selectList(query);
        Menu menu = new Menu();
        menu.setMenuId(0L);
        menu.setParentId(-1L);
        menu.setMenuLabel("顶级菜单");
        menus.add(menu);
        List<Menu> menus1 = MakeMenuTree.makeTree(menus, -1L);
        return menus1;
    }

    @Override
    public List<Menu> getMenuByUserId(Long userId) {
        return this.baseMapper.getMenuByUserId(userId);
    }

    @Override
    public List<Menu> getMenuByUserIdForLiveUser(Long userId) {
        return this.baseMapper.getMenuByUserIdForLiveUser(userId);
    }

    @Override
    public List<Menu> getMenuByRoleId(Long roleId) {
        return this.baseMapper.getMenuByRoleId(roleId);
    }
}
