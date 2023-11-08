package com.jack.web.role.entity;

import com.jack.web.menu.entity.Menu;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RolePermissionVo {

    List<Menu> listmenu = new ArrayList<>();
    private Object[] checkList;
}
