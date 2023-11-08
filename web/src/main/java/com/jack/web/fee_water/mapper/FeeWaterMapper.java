package com.jack.web.fee_water.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jack.web.fee_water.entity.FeeWater;
import org.apache.ibatis.annotations.Param;

public interface FeeWaterMapper extends BaseMapper<FeeWater> {
    IPage<FeeWater> getList(IPage<FeeWater> page, @Param("userName") String userName,
                            @Param("houseNum") String houseNum);
}
