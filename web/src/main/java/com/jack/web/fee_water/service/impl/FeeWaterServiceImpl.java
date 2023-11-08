package com.jack.web.fee_water.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jack.web.fee_water.entity.FeeWater;
import com.jack.web.fee_water.entity.FeeWaterParm;
import com.jack.web.fee_water.mapper.FeeWaterMapper;
import com.jack.web.fee_water.service.FeeWaterService;
import org.springframework.stereotype.Service;

@Service
public class FeeWaterServiceImpl extends ServiceImpl<FeeWaterMapper, FeeWater> implements FeeWaterService {

    @Override
    public IPage<FeeWater> getList(FeeWaterParm feeWaterParm) {
        IPage<FeeWater> page = new Page<>();
        page.setCurrent(feeWaterParm.getCurrentPage());
        page.setSize(feeWaterParm.getPageSize());
        return this.baseMapper.getList(page, feeWaterParm.getUserName(), feeWaterParm.getHouseNum());
    }
}
