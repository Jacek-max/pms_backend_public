package com.jack.web.house_unit.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jack.utils.ResultUtils;
import com.jack.utils.ResultVo;
import com.jack.web.house_unit.entity.HouseUnit;
import com.jack.web.house_unit.entity.HouseUnitParm;
import com.jack.web.house_unit.service.HouseUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/houseUnit")
public class HouseUnitController {

    @Autowired
    private HouseUnitService houseUnitService;

    @GetMapping("/list")
    public ResultVo getList(HouseUnitParm houseUnitParm){

        IPage<HouseUnit> list = houseUnitService.getList(houseUnitParm);
        return ResultUtils.success("查询成功",list);

    }

    @PostMapping
    @PreAuthorize("hasAuthority('sys:houseUnit:add')")
    public ResultVo add(@RequestBody HouseUnit houseUnit){

        boolean save = houseUnitService.save(houseUnit);
        if (save){
            return ResultUtils.success("新增单元成功！");
        }
        return ResultUtils.error("新增单元失败！");
    }

    @PutMapping
    @PreAuthorize("hasAuthority('sys:houseUnit:edit')")
    public ResultVo edit(@RequestBody HouseUnit houseUnit){
        boolean b = houseUnitService.updateById(houseUnit);
        if (b){
            return ResultUtils.success("编辑单元成功！");
        }
        return ResultUtils.error("编辑单元失败！");
    }

    @DeleteMapping("/{unitId}")
    @PreAuthorize("hasAuthority('sys:houseUnit:delete')")
    public ResultVo delete(@PathVariable("unitId") Long unitId){
        boolean b = houseUnitService.removeById(unitId);
        if (b){
            return ResultUtils.success("删除单元成功！");
        }
        return ResultUtils.error("删除单元失败！");
    }

    //根据栋数Id查单元
    @GetMapping("/getUnitListByBuildId")
    public ResultVo getUnitListByBuildId(HouseUnit houseUnit){
        QueryWrapper<HouseUnit> query = new QueryWrapper<>();
        query.lambda().eq(HouseUnit::getBuildId,houseUnit.getBuildId());
        List<HouseUnit> list = houseUnitService.list(query);
        return ResultUtils.success("查询成功",list);
    }
}
