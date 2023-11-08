package com.jack.web.house_list.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jack.web.house_list.entity.HouseList;
import org.apache.ibatis.annotations.Param;

public interface HouseListMapper extends BaseMapper<HouseList> {

    IPage<HouseList> getList(IPage<HouseList> page, @Param("buildName") String buildName,
                             @Param("unitName") String unitName, @Param("houseNum") String houseNum,
                             @Param("status") String status);
}
