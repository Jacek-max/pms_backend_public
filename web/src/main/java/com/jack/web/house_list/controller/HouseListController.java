package com.jack.web.house_list.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jack.utils.ResultUtils;
import com.jack.utils.ResultVo;
import com.jack.web.house_list.entity.HouseCount;
import com.jack.web.house_list.entity.HouseList;
import com.jack.web.house_list.entity.ListParm;
import com.jack.web.house_list.service.HouseListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.List;

@RestController
@RequestMapping("/api/houseList")
public class HouseListController {

    @Autowired
    private HouseListService houseListService;

    @GetMapping("/list")
    public ResultVo getList(ListParm parm) {
        IPage<HouseList> list = houseListService.getList(parm);
        return ResultUtils.success("查询成功", list);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('sys:houseList:add')")
    public ResultVo add(@RequestBody HouseList houseList) {
        boolean save = houseListService.save(houseList);
        if (save) {
            return ResultUtils.success("新增房屋成功");
        }
        return ResultUtils.error("新增房屋失败！");
    }

    @PutMapping
    @PreAuthorize("hasAuthority('sys:houseList:edit')")
    public ResultVo edit(@RequestBody HouseList houseList) {
        boolean b = houseListService.updateById(houseList);
        if (b) {
            return ResultUtils.success("编辑房屋成功");
        }
        return ResultUtils.error("编辑房屋失败！");
    }

    @DeleteMapping("/{houseId}")
    @PreAuthorize("hasAuthority('sys:houseList:delete')")
    public ResultVo delete(@PathVariable("houseId") Long houseId) {
        boolean remove = houseListService.removeById(houseId);
        if (remove) {
            return ResultUtils.success("删除房屋成功");
        }
        return ResultUtils.error("删除房屋失败！");
    }

    //根据单元id获取房屋列表
    @GetMapping("/getHouseByUnitId")
    public ResultVo getHouseByUnitId(HouseList houseList) {
        QueryWrapper<HouseList> query = new QueryWrapper<>();
        query.lambda().eq(HouseList::getUnitId, houseList.getUnitId());
        List<HouseList> list = houseListService.list(query);
        return ResultUtils.success("查询成功", list);

    }

    //获取房屋总数
    @GetMapping("/getHouseCount")
    public ResultVo getHouseCount(){
        int houseCount = houseListService.count();
        QueryWrapper<HouseList> query = new QueryWrapper<>();
        query.lambda().eq(HouseList::getStatus,"1");
        int count = houseListService.count(query);

        int free = houseCount - count;

        DecimalFormat decimalFormat = new DecimalFormat(".00");
        String usage = decimalFormat.format (((float) count / houseCount)*100);
        double houseUsage = Double.parseDouble(usage);

        HouseCount houseCountList = new HouseCount();
        houseCountList.setHouseCount(houseCount);
        houseCountList.setHouseFree(free);
        houseCountList.setHouseUsage(houseUsage);

        return ResultUtils.success("查询成功",houseCountList);
    }

}
