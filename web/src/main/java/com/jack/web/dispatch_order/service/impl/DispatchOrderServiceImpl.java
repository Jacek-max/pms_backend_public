package com.jack.web.dispatch_order.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jack.web.dispatch_order.entity.DispatchOrder;
import com.jack.web.dispatch_order.mapper.DispatchOrderMapper;
import com.jack.web.dispatch_order.service.DispatchOrderService;
import com.jack.web.user_repair.entity.UserRepairParm;
import org.springframework.stereotype.Service;

@Service
public class DispatchOrderServiceImpl extends ServiceImpl<DispatchOrderMapper, DispatchOrder> implements DispatchOrderService {

    @Override
    public IPage<DispatchOrder> getListById(UserRepairParm parm) {
        IPage<DispatchOrder> page = new Page<>();
        page.setSize(parm.getPageSize());
        page.setCurrent(parm.getCurrentPage());
        return this.baseMapper.getListById(page,parm);
    }

}
