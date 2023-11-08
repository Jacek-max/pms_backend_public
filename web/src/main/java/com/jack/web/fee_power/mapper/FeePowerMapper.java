package com.jack.web.fee_power.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jack.web.fee_power.entity.FeePower;
import org.apache.ibatis.annotations.Param;

public interface FeePowerMapper extends BaseMapper<FeePower> {

    IPage<FeePower> getList(IPage<FeePower> page, @Param("userName") String userName,
                            @Param("houseNum") String houseNum);

}
