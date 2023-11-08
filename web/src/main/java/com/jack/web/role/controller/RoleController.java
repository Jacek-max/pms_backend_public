package com.jack.web.role.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jack.utils.ResultUtils;
import com.jack.utils.ResultVo;
import com.jack.web.role.entity.*;
import com.jack.web.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    public ResultVo list(RoleParm parm) {
        IPage<Role> list = roleService.list(parm);
        return ResultUtils.success("查询成功", list);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('sys:role:add')")
    public ResultVo addRole(@RequestBody Role role) {
        boolean save = roleService.save(role);
        if (save) {
            return ResultUtils.success("新增角色成功！");
        }
        return ResultUtils.error("新增角色失败！");
    }

    @PutMapping
    @PreAuthorize("hasAuthority('sys:role:edit')")
    public ResultVo editRole(@RequestBody Role role) {
        boolean save = roleService.updateById(role);
        if (save) {
            return ResultUtils.success("编辑角色成功！");
        }
        return ResultUtils.error("编辑角色失败！");
    }

    @DeleteMapping("/{roleId}")
    @PreAuthorize("hasAuthority('sys:role:delete')")
    public ResultVo deleteRole(@PathVariable("roleId") Long roleId) {
        boolean b = roleService.removeById(roleId);
        if (b) {
            return ResultUtils.success("删除角色成功！");
        }
        return ResultUtils.error("删除角色失败！");
    }

    @GetMapping("/getAssignTree")
    public ResultVo getAssignTree(RoleAssignParm parm) {
        System.out.println(parm);
        RolePermissionVo assignTree = roleService.getAssignTree(parm);
        return ResultUtils.success("查询成功", assignTree);
    }

    //分配权限保存
    @PostMapping("/saveAssign")
    @PreAuthorize("hasAuthority('sys:role:assignRole')")
    public ResultVo saveAssignTree(@RequestBody RolePermissionParm parm){
        roleService.saveAssign(parm);
        return ResultUtils.success("分配权限成功");
    }

    //获取角色列表（无需参数）
    @GetMapping("/getList")
    public ResultVo getList() {
        List<Role> list = roleService.list();
        return ResultUtils.success("查询成功",list);
    }

}
