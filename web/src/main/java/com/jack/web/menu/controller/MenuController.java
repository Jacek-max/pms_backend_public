package com.jack.web.menu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jack.utils.ResultUtils;
import com.jack.utils.ResultVo;
import com.jack.web.menu.entity.Menu;
import com.jack.web.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    public ResultVo list() {
        List<Menu> list = menuService.getList();
        return ResultUtils.success("查询成功", list);

    }

    @PostMapping
    @PreAuthorize("hasAuthority('sys:menu:add')")
    public ResultVo addMenu(@RequestBody Menu menu) {
        boolean save = menuService.save(menu);
        if (save) {
            return ResultUtils.success("新增目录成功！");
        }
        return ResultUtils.error("新增目录失败！");
    }

    @PutMapping
    @PreAuthorize("hasAuthority('sys:menu:edit')")
    public ResultVo editMenu(@RequestBody Menu menu) {
        boolean b = menuService.updateById(menu);
        if (b) {
            return ResultUtils.success("编辑目录成功！");
        }
        return ResultUtils.error("编辑目录失败！");
    }

    @DeleteMapping("/{menuId}")
    @PreAuthorize("hasAuthority('sys:menu:delete')")
    public ResultVo deleteMenu(@PathVariable("menuId") Long menuId) {
        QueryWrapper<Menu> query = new QueryWrapper<>();
        query.lambda().eq(Menu::getParentId, menuId);
        List<Menu> list = menuService.list(query);
        if (list.size() > 0) {
            return ResultUtils.error("该菜单存在下级，不能删除");
        }
        boolean b = menuService.removeById(menuId);
        if (b) {
            return ResultUtils.success("删除目录成功！");
        }
        return ResultUtils.error("删除目录失败！");
    }

    @GetMapping("/parent")
    public ResultVo getParentList() {
        List<Menu> parentList = menuService.getParentList();
        return ResultUtils.success("查询目录成功！", parentList);
    }
}
