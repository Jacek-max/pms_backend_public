package com.jack.web.fee_power.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jack.utils.ResultUtils;
import com.jack.utils.ResultVo;
import com.jack.web.fee_power.entity.FeePower;
import com.jack.web.fee_power.entity.FeePowerParm;
import com.jack.web.fee_power.service.FeePowerService;
import com.jack.web.live_house.entity.LiveHouse;
import com.jack.web.live_house.service.LiveHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/api/feePower")
public class FeePowerController {

    @Autowired
    private FeePowerService feePowerService;

    @Resource
    private LiveHouseService liveHouseService;


    @PostMapping
    @PreAuthorize("hasAuthority('sys:feePower:add')")
    public ResultVo add(@RequestBody FeePower feePower) {
        QueryWrapper<LiveHouse> query = new QueryWrapper<>();
        query.lambda().eq(LiveHouse::getHouseId, feePower.getHouseId())
                .eq(LiveHouse::getUseStatus, "0");
        LiveHouse house = liveHouseService.getOne(query);
        if (house == null) {
            return ResultUtils.error("该房间未被使用，不能添加电费缴费单!");
        }
        feePower.setUserId(house.getUserId());
        feePowerService.saveFeePower(feePower);
        return ResultUtils.success("新增电费缴费单成功");
    }

    @PutMapping
    @PreAuthorize("hasAuthority('sys:feePower:edit')")
    public ResultVo edit(@RequestBody FeePower feePower) {
        QueryWrapper<LiveHouse> query = new QueryWrapper<>();
        query.lambda().eq(LiveHouse::getHouseId, feePower.getHouseId())
                .eq(LiveHouse::getUseStatus, "0");
        LiveHouse house = liveHouseService.getOne(query);
        if (house == null) {
            return ResultUtils.error("该房间未被使用，不能编辑电费缴费单!");
        }
        feePowerService.updateFeePower(feePower);
        return ResultUtils.success("编辑电费缴费单成功");
    }

    @DeleteMapping("/{powerId}")
    @PreAuthorize("hasAuthority('sys:feePower:delete')")
    public ResultVo delete(@PathVariable("powerId") Long powerId) {
        //已缴费，则不能删除缴费单
        QueryWrapper<FeePower> query = new QueryWrapper<>();
        query.lambda().eq(FeePower::getPowerId, powerId).eq(FeePower::getPayPowerStatus, "1");
        FeePower one = feePowerService.getOne(query);
        if (one != null) {
            return ResultUtils.error("已缴费，不能删除！");
        }

        boolean b = feePowerService.removeById(powerId);
        if (b) {
            return ResultUtils.success("删除电费缴费单成功");
        } else {
            return ResultUtils.error("删除电费缴费单失败！");
        }
    }

    @GetMapping("/list")
    public ResultVo getList(FeePowerParm parm) {
        IPage<FeePower> list = feePowerService.getList(parm);
        return ResultUtils.success("查询成功", list);
    }

    @PostMapping("/payPower")
    @PreAuthorize("hasAuthority('sys:feePower:pay')")
    public ResultVo payPower(@RequestBody FeePower feePower) {
        feePower.setPayPowerTime(new Date());
        boolean b = feePowerService.updateById(feePower);
        if (b) {
            return ResultUtils.success("电费缴费成功");
        }
        return ResultUtils.error("电费缴费失败！");
    }

    //业主：我的电费
    @GetMapping("/getMyPowerFee")
    public ResultVo getMyPowerFee(FeePowerParm feePowerParm){
        IPage<FeePower> page = new Page<>();
        page.setCurrent(feePowerParm.getCurrentPage());
        page.setSize(feePowerParm.getPageSize());
        QueryWrapper<FeePower> query = new QueryWrapper<>();
        query.lambda().eq(FeePower::getUserId,feePowerParm.getUserId());
        IPage<FeePower> list = feePowerService.page(page, query);
        return ResultUtils.success("查询成功",list);

    }
}
