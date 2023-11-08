package com.jack.web.role_menu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jack.web.role_menu.entity.RoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMenuMapper extends BaseMapper<RoleMenu> {
    boolean saveRoleMenu(@Param("roleId") Long roleId, @Param("menuIds") List<Long> menuIds);
}
