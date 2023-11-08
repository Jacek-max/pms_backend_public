package com.jack.web.live_user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jack.web.house_list.entity.HouseList;
import com.jack.web.house_list.mapper.HouseListMapper;
import com.jack.web.live_house.entity.LiveHouse;
import com.jack.web.live_house.mapper.LiveHouseMapper;
import com.jack.web.live_park.entity.LivePark;
import com.jack.web.live_park.mapper.LiveParkMapper;
import com.jack.web.live_role.entity.LiveRole;
import com.jack.web.live_role.mapper.LiveRoleMapper;
import com.jack.web.live_user.entity.AssignHouseParm;
import com.jack.web.live_user.entity.LiveUser;
import com.jack.web.live_user.mapper.LiveUserMapper;
import com.jack.web.live_user.service.LiveUserService;
import com.jack.web.park_list.entity.ParkList;
import com.jack.web.park_list.mapper.ParkListMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class LiveUserServiceImpl extends ServiceImpl<LiveUserMapper, LiveUser> implements LiveUserService {

    @Resource
    private LiveRoleMapper liveRoleMapper;

    @Resource
    private LiveHouseMapper liveHouseMapper;

    @Resource
    private HouseListMapper houseListMapper;

    @Resource
    private LiveParkMapper liveParkMapper;

    @Resource
    private ParkListMapper parkListMapper;

    @Override
    @Transactional
    public void saveLiveUser(LiveUser liveUser) {

        this.baseMapper.insert(liveUser);

        LiveRole liveRole = new LiveRole();
        liveRole.setRoleId(liveUser.getRoleId());
        liveRole.setUserId(liveUser.getUserId());
        liveRoleMapper.insert(liveRole);
    }

    @Override
    public IPage<LiveUser> getList(IPage<LiveUser> page, String userName, String phone) {
        return this.baseMapper.getList(page, userName, phone);
    }

    @Override
    @Transactional
    public void editLiveUser(LiveUser liveUser) {
        this.baseMapper.updateById(liveUser);
        QueryWrapper<LiveRole> query = new QueryWrapper<>();
        query.lambda().eq(LiveRole::getUserId, liveUser.getUserId());
        liveRoleMapper.delete(query);

        LiveRole liveRole = new LiveRole();
        liveRole.setRoleId(liveUser.getRoleId());
        liveRole.setUserId(liveUser.getUserId());
        liveRoleMapper.insert(liveRole);
    }

    @Override
    public LiveUser getUser(Long userId) {
        return this.baseMapper.getUser(userId);
    }

    @Override
    @Transactional
    public void assignHouse(AssignHouseParm parm) {
        LiveHouse liveHouse = new LiveHouse();
        liveHouse.setHouseId(parm.getHouseId());
        liveHouse.setUserId(parm.getUserId());
        liveHouse.setUseStatus("0");
        liveHouseMapper.insert(liveHouse);

        HouseList house = new HouseList();
        house.setHouseId(parm.getHouseId());
        house.setStatus("1");
        houseListMapper.updateById(house);

    }

    @Override
    @Transactional
    public void assignSavePark(LivePark livePark) {
        livePark.setLiveStatue("0");
        liveParkMapper.insert(livePark);
        ParkList parkList = new ParkList();
        parkList.setParkId(livePark.getParkId());
        parkList.setParkStatus("1");
        parkListMapper.updateById(parkList);

    }

    @Override
    @Transactional
    public void returnHouse(AssignHouseParm parm) {

        //更新租户和房屋关系表状态为解绑
        LiveHouse liveHouse = new LiveHouse();
        liveHouse.setUseStatus("1");
        QueryWrapper<LiveHouse> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(LiveHouse::getHouseId, parm.getHouseId())
                .eq(LiveHouse::getUserId, parm.getUserId())
                .eq(LiveHouse::getUseStatus,"0");
        liveHouseMapper.update(liveHouse, queryWrapper);

        //更新房屋表的使用状态为未使用
        HouseList houseList = new HouseList();
        houseList.setStatus("0");
        QueryWrapper<HouseList> query = new QueryWrapper<>();
        query.lambda().eq(HouseList::getHouseId, parm.getHouseId());
        houseListMapper.update(houseList, query);
    }

    @Override
    @Transactional
    public void returnPark(LivePark livePark) {
        //更新租户和车位的关系为解绑
        QueryWrapper<LivePark> query = new QueryWrapper<>();
        query.lambda().eq(LivePark::getParkId, livePark.getParkId())
                .eq(LivePark::getUserId, livePark.getUserId())
                .eq(LivePark::getLiveStatue,"0");
        LivePark nLivePark = new LivePark();
        nLivePark.setLiveStatue("1");
        liveParkMapper.update(nLivePark,query);

        //更新车位的使用状态为未使用
        ParkList parkList = new ParkList();
        parkList.setParkStatus("0");
        parkList.setParkId(livePark.getParkId());
        parkListMapper.updateById(parkList);
    }

    @Override
    public LiveUser loadUser(String username) {
        QueryWrapper<LiveUser> query = new QueryWrapper<>();
        query.lambda().eq(LiveUser::getUsername,username);
        return this.baseMapper.selectOne(query);
    }
}
