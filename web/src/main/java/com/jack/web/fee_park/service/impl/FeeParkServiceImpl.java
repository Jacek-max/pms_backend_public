package com.jack.web.fee_park.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jack.web.fee_park.entity.FeePark;
import com.jack.web.fee_park.entity.FeeParkParm;
import com.jack.web.fee_park.mapper.FeeParkMapper;
import com.jack.web.fee_park.service.FeeParkService;
import org.springframework.stereotype.Service;

@Service
public class FeeParkServiceImpl extends ServiceImpl<FeeParkMapper, FeePark> implements FeeParkService {

    @Override
    public IPage<FeePark> getList(FeeParkParm parkParm) {
        IPage<FeePark> page = new Page<>();
        page.setSize(parkParm.getPageSize());
        page.setCurrent(parkParm.getCurrentPage());
        return this.baseMapper.getList(page,parkParm.getLoginName(),parkParm.getParkName());
    }
}
