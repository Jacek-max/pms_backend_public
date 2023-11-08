package com.jack.web.dispatch_order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jack.web.dispatch_order.entity.DispatchOrder;
import com.jack.web.user_repair.entity.UserRepairParm;

public interface DispatchOrderService extends IService<DispatchOrder> {
    IPage<DispatchOrder> getListById(UserRepairParm parm);
}
