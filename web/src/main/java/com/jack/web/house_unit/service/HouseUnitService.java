package com.jack.web.house_unit.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jack.web.house_unit.entity.HouseUnit;
import com.jack.web.house_unit.entity.HouseUnitParm;

public interface HouseUnitService extends IService<HouseUnit> {

    IPage<HouseUnit> getList(HouseUnitParm houseUnitParm);
}
