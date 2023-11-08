package com.jack.web.house_unit.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jack.web.house_unit.entity.HouseUnit;
import com.jack.web.house_unit.entity.HouseUnitParm;
import com.jack.web.house_unit.mapper.HouseUnitMapper;
import com.jack.web.house_unit.service.HouseUnitService;
import org.springframework.stereotype.Service;

@Service
public class HouseUnitServiceImpl extends ServiceImpl<HouseUnitMapper, HouseUnit> implements HouseUnitService {
    @Override
    public IPage<HouseUnit> getList(HouseUnitParm houseUnitParm) {
        IPage<HouseUnit> page = new Page<>();
        page.setSize(houseUnitParm.getPageSize());
        page.setCurrent(houseUnitParm.getCurrentPage());
        return this.baseMapper.getList(page,houseUnitParm.getUnitName(),houseUnitParm.getBuildName());
    }
}
