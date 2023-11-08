package com.jack.web.park_list.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jack.utils.ResultUtils;
import com.jack.utils.ResultVo;
import com.jack.web.park_list.entity.ParkCount;
import com.jack.web.park_list.entity.ParkList;
import com.jack.web.park_list.entity.ParkListParm;
import com.jack.web.park_list.service.ParkListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.List;

@RestController
@RequestMapping("/api/parkList")
public class ParkListController {

    @Autowired
    private ParkListService parkListService;

    @GetMapping("/list")
    public ResultVo getList(ParkListParm parkListParm){
        IPage<ParkList> list = parkListService.getList(parkListParm);
        return ResultUtils.success("查询成功",list);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('sys:parkList:add')")
    public ResultVo add(@RequestBody ParkList parkList){
        boolean save = parkListService.save(parkList);
        if (save){
            return ResultUtils.success("新增车位成功");
        }
        return ResultUtils.error("新增车位失败！");
    }

    @PutMapping
    @PreAuthorize("hasAuthority('sys:parkList:edit')")
    public ResultVo edit(@RequestBody ParkList parkList){
        boolean b = parkListService.updateById(parkList);
        if (b){
            return ResultUtils.success("编辑车位成功");
        }
        return ResultUtils.error("编辑车位失败！");
    }

    @DeleteMapping("/{parkId}")
    @PreAuthorize("hasAuthority('sys:parkList:delete')")
    public ResultVo delete(@PathVariable("parkId") Long parkId){
        boolean b = parkListService.removeById(parkId);
        if (b){
            return ResultUtils.success("删除车位成功");
        }
        return ResultUtils.error("删除车位失败！");
    }

    //无分页查询
    @GetMapping("/listNoPage")
    public ResultVo getListNoPage(){
        List<ParkList> list = parkListService.list();
        return ResultUtils.success("查询成功",list);
    }

    //获取车位数据总数
    @GetMapping("/getParkCount")
    public ResultVo getHouseCount(){
        int parkCount = parkListService.count();
        QueryWrapper<ParkList> query = new QueryWrapper<>();
        query.lambda().eq(ParkList::getParkStatus,"1");
        int count = parkListService.count(query);

        int free = parkCount - count;

        DecimalFormat decimalFormat = new DecimalFormat(".00");
        String usage = decimalFormat.format (((float) count / parkCount)*100);
        double parkUsage = Double.parseDouble(usage);

        ParkCount parkCountList = new ParkCount();
        parkCountList.setParkCount(parkCount);
        parkCountList.setParkFree(free);
        parkCountList.setParkUsage(parkUsage);

        return ResultUtils.success("查询成功",parkCountList);
    }
}
