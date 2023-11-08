package com.jack.web.dispatch_order.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jack.utils.ResultUtils;
import com.jack.utils.ResultVo;
import com.jack.web.dispatch_order.entity.DispatchOrder;
import com.jack.web.dispatch_order.service.DispatchOrderService;
import com.jack.web.user_repair.entity.UserRepairParm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dispatchOrder")
public class DispatchOrderController {

    @Autowired
    private DispatchOrderService dispatchOrderService;

    @GetMapping("/getOrderList")
    public ResultVo getList(UserRepairParm parm) {
        IPage<DispatchOrder> list = dispatchOrderService.getListById(parm);
        return ResultUtils.success("", list);
    }
}
