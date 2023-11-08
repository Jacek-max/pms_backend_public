package com.jack.web.fee_water.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jack.utils.ResultUtils;
import com.jack.utils.ResultVo;
import com.jack.web.fee_water.entity.FeeWater;
import com.jack.web.fee_water.entity.FeeWaterParm;
import com.jack.web.fee_water.service.FeeWaterService;
import com.jack.web.live_house.entity.LiveHouse;
import com.jack.web.live_house.service.LiveHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/feeWater")
public class FeeWaterController {

    @Autowired
    private FeeWaterService feeWaterService;

    @Autowired
    private LiveHouseService liveHouseService;

    @PostMapping
    @PreAuthorize("hasAuthority('sys:feeWater:add')")
    public ResultVo add(@RequestBody FeeWater feeWater) {
        QueryWrapper<LiveHouse> query = new QueryWrapper<>();
        query.lambda().eq(LiveHouse::getHouseId, feeWater.getHouseId())
                .eq(LiveHouse::getUseStatus, "0");
        LiveHouse house = liveHouseService.getOne(query);
        if (house == null) {
            return ResultUtils.error("该房间未被使用，不能添加水费缴费单!");
        }
        feeWater.setUserId(house.getUserId());
        feeWaterService.save(feeWater);
        return ResultUtils.success("新增水费缴费单成功");
    }

    @PutMapping
    @PreAuthorize("hasAuthority('sys:feeWater:edit')")
    public ResultVo edit(@RequestBody FeeWater feeWater) {
        QueryWrapper<LiveHouse> query = new QueryWrapper<>();
        query.lambda().eq(LiveHouse::getHouseId, feeWater.getHouseId())
                .eq(LiveHouse::getUseStatus, "0");
        LiveHouse house = liveHouseService.getOne(query);
        if (house == null) {
            return ResultUtils.error("该房间未被使用，不能编辑水费缴费单!");
        }
        //如以完成缴费，则不能编辑缴费单
        feeWaterService.updateById(feeWater);
        return ResultUtils.success("编辑水费缴费单成功");
    }

    @DeleteMapping("/{waterId}")
    @PreAuthorize("hasAuthority('sys:feeWater:delete')")
    public ResultVo delete(@PathVariable("waterId") Long powerId) {
        //已缴费，则不能删除缴费单
        QueryWrapper<FeeWater> query = new QueryWrapper<>();
        query.lambda().eq(FeeWater::getWaterId, powerId).eq(FeeWater::getPayWaterStatus, "1");
        FeeWater one = feeWaterService.getOne(query);
        if (one != null) {
            return ResultUtils.error("已缴费，不能删除！");
        }

        boolean b = feeWaterService.removeById(powerId);
        if (b) {
            return ResultUtils.success("删除水费缴费单成功");
        } else {
            return ResultUtils.error("删除水费缴费单失败！");
        }
    }

    @GetMapping("/list")
    public ResultVo getList(FeeWaterParm parm) {
        IPage<FeeWater> list = feeWaterService.getList(parm);
        return ResultUtils.success("查询成功", list);
    }

    @PostMapping("/payWater")
    @PreAuthorize("hasAuthority('sys:feeWater:pay')")
    public ResultVo payWater(@RequestBody FeeWater feeWater) {
        feeWater.setPayWaterTime(new Date());
        feeWater.setPayWaterStatus("1");
        boolean b = feeWaterService.updateById(feeWater);
        if (b) {
            return ResultUtils.success("水费缴费成功");
        }
        return ResultUtils.error("水费缴费失败！");
    }

    //业主：我的水费
    @GetMapping("/getMyWaterFee")
    public ResultVo getMyWaterFee(FeeWaterParm feeWaterParm){
        IPage<FeeWater> page = new Page<>();
        page.setCurrent(feeWaterParm.getCurrentPage());
        page.setSize(feeWaterParm.getPageSize());
        QueryWrapper<FeeWater> query = new QueryWrapper<>();
        query.lambda().eq(FeeWater::getUserId,feeWaterParm.getUserId());
        IPage<FeeWater> list = feeWaterService.page(page, query);
        return ResultUtils.success("查询成功",list);

    }
}
