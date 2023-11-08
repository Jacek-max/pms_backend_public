package com.jack.web.house_building.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jack.web.house_building.entity.HouseBuilding;
import com.jack.web.house_building.entity.HouseBuildingParm;
import com.jack.web.house_building.mapper.HouseBuildingMapper;
import com.jack.web.house_building.service.HouseBuildingService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class HouseBuildingServiceImpl extends ServiceImpl<HouseBuildingMapper, HouseBuilding> implements HouseBuildingService {

    @Override
    public IPage<HouseBuilding> getList(HouseBuildingParm parm) {
        QueryWrapper<HouseBuilding> query = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(parm.getName())){
            query.lambda().like(HouseBuilding::getName,parm.getName());
        }
        if (StringUtils.isNotEmpty(parm.getType())){
            query.lambda().eq(HouseBuilding::getType,parm.getType());
        }

        //构造分页对象
        IPage<HouseBuilding> page = new Page<>();
        page.setSize(parm.getPageSize());
        page.setCurrent(parm.getCurrentPage());

        return this.baseMapper.selectPage(page,query);
    }
}
