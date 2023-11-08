package com.jack.web.live_user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jack.utils.ResultUtils;
import com.jack.utils.ResultVo;
import com.jack.web.fee_park.entity.FeePark;
import com.jack.web.fee_park.service.FeeParkService;
import com.jack.web.fee_power.entity.FeePower;
import com.jack.web.fee_power.service.FeePowerService;
import com.jack.web.fee_water.entity.FeeWater;
import com.jack.web.fee_water.service.FeeWaterService;
import com.jack.web.live_park.entity.LivePark;
import com.jack.web.live_user.entity.AssignHouseParm;
import com.jack.web.live_user.entity.LiveUser;
import com.jack.web.live_user.entity.LiveUserParm;
import com.jack.web.live_user.service.LiveUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/liveUser")
public class LiveUserController {

    @Autowired
    private LiveUserService liveUserService;

    @Autowired
    private FeeWaterService feeWaterService;

    @Autowired
    private FeePowerService feePowerService;

    @Autowired
    private FeeParkService feeParkService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    @PreAuthorize("hasAuthority('sys:liveUser:add')")
    public ResultVo add(@RequestBody LiveUser liveUser) {

        QueryWrapper<LiveUser> query = new QueryWrapper<>();
        query.lambda().eq(LiveUser::getUsername, liveUser.getUsername());
        LiveUser one = liveUserService.getOne(query);
        if (one != null) {
            return ResultUtils.error("登录名已被占用");
        }

//        liveUser.setPassword(DigestUtils.md5DigestAsHex(liveUser.getPassword().getBytes()));
        liveUser.setPassword(passwordEncoder.encode(liveUser.getPassword()));

        liveUserService.saveLiveUser(liveUser);
        return ResultUtils.success("新增业主成功");
    }

    @GetMapping("/list")
    public ResultVo getList(LiveUserParm liveUserParm) {
        IPage<LiveUser> page = new Page<>();
        page.setSize(liveUserParm.getPageSize());
        page.setCurrent(liveUserParm.getCurrentPage());
        IPage<LiveUser> list = liveUserService.getList(page, liveUserParm.getLoginName(), liveUserParm.getPhone());
        return ResultUtils.success("查询成功", list);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('sys:liveUser:edit')")
    public ResultVo edit(@RequestBody LiveUser liveUser) {

        QueryWrapper<LiveUser> query = new QueryWrapper<>();
        query.lambda().eq(LiveUser::getUsername, liveUser.getUsername());
        LiveUser one = liveUserService.getOne(query);
        if (one != null && !one.getUserId().equals(liveUser.getUserId())) {
            return ResultUtils.error("登录名已被占用");
        }

        liveUserService.editLiveUser(liveUser);
        return ResultUtils.success("编辑业主成功");

    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('sys:liveUser:delete')")
    public ResultVo delete(@PathVariable("userId") Long userId){
        boolean b = liveUserService.removeById(userId);
        if (b){
            return ResultUtils.success("删除业主成功");
        }
        return ResultUtils.error("删除业主失败");
    }

    //编辑查询回显
    @GetMapping("/getUserById")
    public ResultVo getUserById(LiveUser liveUser) {
        LiveUser user = liveUserService.getUser(liveUser.getUserId());
        return ResultUtils.success("查询成功", user);

    }

    //分配房屋保存
    @PostMapping("/assignSava")
    @PreAuthorize("hasAuthority('sys:liveUser:assignHome')")
    public ResultVo assignSava(@RequestBody AssignHouseParm parm) {
        liveUserService.assignHouse(parm);
        return ResultUtils.success("分配房屋成功");
    }

    //分配车位保存
    @PostMapping("/assignParkSava")
    @PreAuthorize("hasAuthority('sys:liveUser:assignCar')")
    public ResultVo assignParkSava(@RequestBody LivePark livePark) {
        liveUserService.assignSavePark(livePark);
        return ResultUtils.success("分配车位成功");
    }

    //退房
    @PostMapping("/returnHouse")
    @PreAuthorize("hasAuthority('sys:liveUser:returnHome')")
    public ResultVo returnHouse(@RequestBody AssignHouseParm parm) {
        //查询水费
        QueryWrapper<FeeWater> queryWater = new QueryWrapper<>();
        queryWater.lambda().eq(FeeWater::getHouseId, parm.getHouseId())
                .eq(FeeWater::getUserId, parm.getUserId())
                .eq(FeeWater::getPayWaterStatus, "0");
        List<FeeWater> list = feeWaterService.list(queryWater);
        if (list != null && list.size() > 0) {
            return ResultUtils.error("请缴水费之后再退房!");
        }

        //查询电费
        QueryWrapper<FeePower> queryPower = new QueryWrapper<>();
        queryPower.lambda().eq(FeePower::getHouseId, parm.getHouseId())
                .eq(FeePower::getUserId, parm.getUserId())
                .eq(FeePower::getPayPowerStatus, "0");
        List<FeePower> powerList = feePowerService.list(queryPower);
        if (powerList != null && powerList.size() > 0) {
            return ResultUtils.error("请缴电费之后再退房!");
        }

        liveUserService.returnHouse(parm);
        return ResultUtils.success("退房成功");
    }

    //退车位
    @PostMapping("/returnPark")
    @PreAuthorize("hasAuthority('sys:liveUser:returnCar')")
    public ResultVo returnPark(@RequestBody LivePark livePark){
        QueryWrapper<FeePark> query = new QueryWrapper<>();
        query.lambda().eq(FeePark::getParkId,livePark.getParkId())
                .eq(FeePark::getUserId,livePark.getUserId())
                .eq(FeePark::getPayParkStatus,"0");
        List<FeePark> list = feeParkService.list(query);
        if(list != null && list.size()>0){
            return ResultUtils.error("请缴停车费后再退车位！");
        }

        liveUserService.returnPark(livePark);
        return ResultUtils.success("退车位成功");
    }
}
