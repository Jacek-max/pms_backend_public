package com.jack.web.role_menu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jack.web.role_menu.entity.RoleMenu;
import com.jack.web.role_menu.mapper.RoleMenuMapper;
import com.jack.web.role_menu.service.RoleMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

    @Override
    public void saveRoleMenu(Long roleId, List<Long> menuIds) {
        this.baseMapper.saveRoleMenu(roleId, menuIds);
    }
}
