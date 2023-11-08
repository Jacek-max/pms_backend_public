package com.jack.web.role.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jack.web.menu.entity.MakeMenuTree;
import com.jack.web.menu.entity.Menu;
import com.jack.web.menu.service.MenuService;
import com.jack.web.role.entity.*;
import com.jack.web.role.mapper.RoleMapper;
import com.jack.web.role.service.RoleService;
import com.jack.web.role_menu.entity.RoleMenu;
import com.jack.web.role_menu.service.RoleMenuService;
import com.jack.web.user.entity.User;
import com.jack.web.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public IPage<Role> list(RoleParm parm) {
        QueryWrapper<Role> query = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(parm.getRoleName())) {
            query.lambda().like(Role::getRoleName, parm.getRoleName());
        }
        IPage<Role> page = new Page<>();
        page.setSize(parm.getPageSize());
        page.setCurrent(parm.getCurrentPage());
        return this.baseMapper.selectPage(page, query);
    }

    @Override
    public RolePermissionVo getAssignTree(RoleAssignParm parm) {
        //查询当前用户的信息
        User user = userService.getById(parm.getUserId());
        //查询权限
        List<Menu> list = null;
        if(user.getIsAdmin().equals("1")){ //如果是超级管理员，拥有全部权限
            list =menuService.list();
        }else{ //如果不是，根据用户id查询
            list = menuService.getMenuByUserId(parm.getUserId());
        }
        //组装成树的格式
        List<Menu> menusList = MakeMenuTree.makeTree(list, 0L);
        //查询角色原来的权限
        List<Menu> roleList = menuService.getMenuByRoleId(parm.getRoleId());
        //存角色原来权限的id
        List<Long> ids = new ArrayList<>();
        Optional.ofNullable(roleList).orElse(new ArrayList<>()).stream().filter(item ->item != null).forEach(item->{
            ids.add(item.getMenuId());
        });
        RolePermissionVo vo = new RolePermissionVo();
        vo.setListmenu(menusList);
        vo.setCheckList(ids.toArray());
        return vo;
    }

    @Override
    @Transactional
    public void saveAssign(RolePermissionParm parm) {
        //删除原来的权限
        QueryWrapper<RoleMenu> query = new QueryWrapper<>();
        query.lambda().eq(RoleMenu::getRoleId,parm.getRoleId());
        roleMenuService.remove(query);
        //保存新权限
        roleMenuService.saveRoleMenu(parm.getRoleId(),parm.getList());
    }
}
