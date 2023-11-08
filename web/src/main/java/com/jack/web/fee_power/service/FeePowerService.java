package com.jack.web.fee_power.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jack.web.fee_power.entity.FeePower;
import com.jack.web.fee_power.entity.FeePowerParm;

public interface FeePowerService extends IService<FeePower> {

    void saveFeePower(FeePower feePower);

    void updateFeePower(FeePower feePower);

    IPage<FeePower> getList(FeePowerParm feePowerParm);
}
