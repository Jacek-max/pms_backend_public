package com.jack.web.menu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jack.web.menu.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> getMenuByUserId(@Param("userId") Long userId);

    //根据业主信息查用户权限
    List<Menu> getMenuByUserIdForLiveUser(@Param("userId") Long userId);

    List<Menu> getMenuByRoleId(@Param("roleId") Long roleId);
}
