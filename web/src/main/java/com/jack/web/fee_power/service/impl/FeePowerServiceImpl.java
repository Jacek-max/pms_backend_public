package com.jack.web.fee_power.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jack.web.fee_power.entity.FeePower;
import com.jack.web.fee_power.entity.FeePowerParm;
import com.jack.web.fee_power.mapper.FeePowerMapper;
import com.jack.web.fee_power.service.FeePowerService;
import com.jack.web.live_house.entity.LiveHouse;
import com.jack.web.live_house.mapper.LiveHouseMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FeePowerServiceImpl extends ServiceImpl<FeePowerMapper, FeePower> implements FeePowerService {

    @Resource
    private LiveHouseMapper liveHouseMapper;

    @Override
    public void saveFeePower(FeePower feePower) {

        this.baseMapper.insert(feePower);
    }

    @Override
    public void updateFeePower(FeePower feePower) {
        QueryWrapper<LiveHouse> query = new QueryWrapper<>();
        query.lambda().eq(LiveHouse::getHouseId, feePower.getHouseId())
                .eq(LiveHouse::getUseStatus, "0");
        LiveHouse house = liveHouseMapper.selectOne(query);
        feePower.setUserId(house.getUserId());
        this.baseMapper.updateById(feePower);
    }

    @Override
    public IPage<FeePower> getList(FeePowerParm feePowerParm) {
        IPage<FeePower> page = new Page<>();
        page.setCurrent(feePowerParm.getCurrentPage());
        page.setSize(feePowerParm.getPageSize());
        return this.baseMapper.getList(page,feePowerParm.getUserName(),feePowerParm.getHouseNum());
    }
}
