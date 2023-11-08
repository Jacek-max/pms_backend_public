package com.jack.web.fee_water.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jack.web.fee_water.entity.FeeWater;
import com.jack.web.fee_water.entity.FeeWaterParm;

public interface FeeWaterService extends IService<FeeWater> {

    IPage<FeeWater> getList(FeeWaterParm feeWaterParm);

}
