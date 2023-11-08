package com.jack.web.dispatch_order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jack.web.dispatch_order.entity.DispatchOrder;
import com.jack.web.user_repair.entity.UserRepairParm;
import org.apache.ibatis.annotations.Param;

public interface DispatchOrderMapper extends BaseMapper<DispatchOrder> {
    IPage<DispatchOrder> getListById(IPage<DispatchOrder> page, @Param("parm") UserRepairParm parm);
}
