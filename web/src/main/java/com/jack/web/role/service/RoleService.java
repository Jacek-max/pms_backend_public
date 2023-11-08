package com.jack.web.role.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jack.web.role.entity.*;

public interface RoleService extends IService<Role> {

    IPage<Role> list(RoleParm parm);

    RolePermissionVo getAssignTree(RoleAssignParm parm);

    void saveAssign(RolePermissionParm parm);
}
