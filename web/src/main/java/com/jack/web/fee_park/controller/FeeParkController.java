package com.jack.web.fee_park.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jack.utils.ResultUtils;
import com.jack.utils.ResultVo;
import com.jack.web.fee_park.entity.FeePark;
import com.jack.web.fee_park.entity.FeeParkParm;
import com.jack.web.fee_park.service.FeeParkService;
import com.jack.web.live_park.entity.LivePark;
import com.jack.web.live_park.mapper.LiveParkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/api/feePark")
public class FeeParkController {

    @Autowired
    private FeeParkService feeParkService;

    @Resource
    private LiveParkMapper liveParkMapper;

    @PostMapping
    @PreAuthorize("hasAuthority('sys:feePark:add')")
    public ResultVo add(@RequestBody FeePark feePark) {
        QueryWrapper<LivePark> query = new QueryWrapper<>();
        query.lambda().eq(LivePark::getParkId, feePark.getParkId())
                .eq(LivePark::getLiveStatue, "0");
        LivePark livePark = liveParkMapper.selectOne(query);

        if (livePark == null) {
            return ResultUtils.error("该车位暂无人员使用,不能添加缴费单!");
        }
        feePark.setUserId(livePark.getUserId());
        boolean save = feeParkService.save(feePark);
        if (save) {
            return ResultUtils.success("新增停车费缴费单成功");
        }
        return ResultUtils.error("新增停车费缴费单失败！");
    }

    @PutMapping
    @PreAuthorize("hasAuthority('sys:feePark:edit')")
    public ResultVo edit(@RequestBody FeePark feePark) {
        QueryWrapper<LivePark> query = new QueryWrapper<>();
        query.lambda().eq(LivePark::getParkId, feePark.getParkId())
                .eq(LivePark::getLiveStatue, "0");
        LivePark livePark = liveParkMapper.selectOne(query);

        if (livePark == null) {
            return ResultUtils.error("该车位暂无人员使用，不能添加缴费单!");
        }
        feePark.setUserId(livePark.getUserId());

        boolean b = feeParkService.updateById(feePark);
        if (b) {
            return ResultUtils.success("编辑停车费缴费单成功");
        }
        return ResultUtils.error("编辑停车费缴费单失败！");
    }

    @DeleteMapping("/{parkFeeId}")
    @PreAuthorize("hasAuthority('sys:feePark:delete')")
    public ResultVo delete(@PathVariable("parkFeeId") Long parkFeeId) {
        QueryWrapper<FeePark> query = new QueryWrapper<>();
        query.lambda().eq(FeePark::getPayParkStatus, "1")
                .eq(FeePark::getParkFeeId, parkFeeId);
        FeePark one = feeParkService.getOne(query);
        if (one != null) {
            return ResultUtils.error("已缴费，不能删除！");
        }
        boolean b = feeParkService.removeById(parkFeeId);
        if (b) {
            return ResultUtils.success("删除停车费缴费单成功");
        }
        return ResultUtils.error("删除停车费缴费单失败！");
    }

    @PostMapping("/pay")
    @PreAuthorize("hasAuthority('sys:feePark:pay')")
    public ResultVo payPark(@RequestBody FeePark feePark){
        feePark.setPayParkTime(new Date());
        feePark.setPayParkStatus("1");
        boolean b = feeParkService.updateById(feePark);
        if (b) {
            return ResultUtils.success("停车费缴费成功");
        }
        return ResultUtils.error("停车费缴费失败！");
    }

    @GetMapping("/list")
    public ResultVo getList(FeeParkParm feeParkParm){
        IPage<FeePark> list = feeParkService.getList(feeParkParm);
        return ResultUtils.success("查询成功",list);
    }

    //业主：我的停车费
    @GetMapping("/getMyParkFee")
    public ResultVo getMyParkFee(FeeParkParm feeParkParm){
        IPage<FeePark> page = new Page<>();
        page.setCurrent(feeParkParm.getCurrentPage());
        page.setSize(feeParkParm.getPageSize());
        QueryWrapper<FeePark> query = new QueryWrapper<>();
        query.lambda().eq(FeePark::getUserId,feeParkParm.getUserId());
        IPage<FeePark> list = feeParkService.page(page, query);
        return ResultUtils.success("查询成功",list);

    }
}
