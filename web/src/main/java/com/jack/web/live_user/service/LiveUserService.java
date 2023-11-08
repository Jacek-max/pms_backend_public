package com.jack.web.live_user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jack.web.live_park.entity.LivePark;
import com.jack.web.live_user.entity.AssignHouseParm;
import com.jack.web.live_user.entity.LiveUser;

public interface LiveUserService extends IService<LiveUser> {

    void saveLiveUser(LiveUser liveUser);

    IPage<LiveUser> getList(IPage<LiveUser> page, String loginName, String phone);

    void editLiveUser(LiveUser liveUser);

    LiveUser getUser(Long userId);

    //分配房屋
    void assignHouse(AssignHouseParm assignHouseParm);

    //分配车位
    void assignSavePark(LivePark livePark);

    //退房
    void returnHouse(AssignHouseParm parm);

    //退车位
    void returnPark(LivePark livePark);

    //根据用户名查询用户
    LiveUser loadUser(String username);

}
