package com.jack.web.role_menu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jack.web.role_menu.entity.RoleMenu;

import java.util.List;

public interface RoleMenuService extends IService<RoleMenu> {
    void saveRoleMenu(Long roleId, List<Long> menuIds);
}

