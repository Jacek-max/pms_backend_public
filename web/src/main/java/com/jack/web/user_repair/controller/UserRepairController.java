package com.jack.web.user_repair.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jack.utils.CommonUtils;
import com.jack.utils.ResultUtils;
import com.jack.utils.ResultVo;
import com.jack.web.dispatch_order.entity.DispatchOrder;
import com.jack.web.dispatch_order.service.DispatchOrderService;
import com.jack.web.user.service.UserService;
import com.jack.web.user_repair.entity.DispatchParm;
import com.jack.web.user_repair.entity.UserRepair;
import com.jack.web.user_repair.entity.UserRepairParm;
import com.jack.web.user_repair.service.UserRepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/userRepair")
public class UserRepairController {

    @Autowired
    private UserRepairService userRepairService;

    @Autowired
    private DispatchOrderService dispatchOrderService;

    @Autowired
    private UserService userService;

    //我的保修
    @GetMapping("/myList")
    public ResultVo getMyList(UserRepairParm parm) {
//        QueryWrapper<UserRepair> query = new QueryWrapper<>();
//        query.lambda().eq(UserRepair::getUserId, parm.getUserId())
//                .like(UserRepair::getRepairContent, parm.getRepairContent())
//                .orderByDesc(UserRepair::getCommitTime);
//
//        IPage<UserRepair> page = new Page<>();
//        page.setSize(parm.getPageSize());
//        page.setCurrent(parm.getCurrentPage());
        IPage<UserRepair> list = userRepairService.getListById(parm);
        return ResultUtils.success("查询成功", list);
    }

    //保修列表
    @GetMapping("/list")
    public ResultVo getList(UserRepairParm parm) {
//        QueryWrapper<UserRepair> query = new QueryWrapper<>();
//        query.lambda().like(UserRepair::getRepairContent, parm.getRepairContent())
//                .orderByDesc(UserRepair::getCommitTime);

//        IPage<UserRepair> page = new Page<>();
//        page.setSize(parm.getPageSize());
//        page.setCurrent(parm.getCurrentPage());
//        IPage<UserRepair> list = userRepairService.page(page, query);
        IPage<UserRepair> list = userRepairService.getList(parm);
        return ResultUtils.success("查询成功", list);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('sys:myRepair:add')")
    public ResultVo add(@RequestBody UserRepair userRepair) {
        userRepair.setCommitTime(new Date());
        userRepair.setStatus("0");
        userRepair.setOrderNum("R" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + CommonUtils.getRandomCode(4));
        boolean save = userRepairService.save(userRepair);
        if (save) {
            return ResultUtils.success("报修成功");
        }
        return ResultUtils.error("报修失败！");
    }

    @PutMapping
    @PreAuthorize("hasAuthority('sys:myRepair:edit')" + "||hasAuthority('sys:repairList:do')" + "||hasAnyAuthority('sys:repairList:confirm')" + "||hasAnyAuthority('sys:repairOrder:do')")
    @Transactional(rollbackFor = Exception.class)
    public ResultVo edit(@RequestBody UserRepair userRepair) {
        boolean b = userRepairService.updateById(userRepair);
        LambdaQueryWrapper<DispatchOrder> query = new LambdaQueryWrapper<>();
        query.eq(DispatchOrder::getOrderSource, userRepair.getRepairId());
        DispatchOrder order = dispatchOrderService.getOne(query);
        if (order != null) {
            if (userRepair.getStatus().equals("2")) {
                order.setStatus("1");
            } else if (userRepair.getStatus().equals("3")) {
                order.setStatus("2");
            }
            dispatchOrderService.updateById(order);
        }
        if (b) {
            return ResultUtils.success("编辑报修单成功");
        }
        return ResultUtils.error("编辑报修单失败！");
    }


    @DeleteMapping("/{repairId}")
    @PreAuthorize("hasAuthority('sys:myRepair:delete')")
    public ResultVo delete(@PathVariable("repairId") Long repairId) {
        boolean b = userRepairService.removeById(repairId);
        if (b) {
            return ResultUtils.success("删除报修单成功");
        }
        return ResultUtils.error("删除报修单失败！");
    }

    //获取待维修数量
    @GetMapping("/getWaitingRepair")
    public ResultVo getWaitingRepair() {
        QueryWrapper<UserRepair> query = new QueryWrapper<>();
        query.lambda().eq(UserRepair::getStatus, "0");
        int count = userRepairService.count(query);
        return ResultUtils.success("查询成功", count);
    }

    //我的未处理投诉
    @GetMapping("/getMyRepairNo")
    public ResultVo getMyRepairNo(UserRepairParm parm) {
        QueryWrapper<UserRepair> query = new QueryWrapper<>();
        query.lambda().eq(UserRepair::getUserId, parm.getUserId())
                .eq(UserRepair::getStatus, "0");
        int count = userRepairService.count(query);
        return ResultUtils.success("查询成功", count);
    }

    @PostMapping("/dispatch")
    @Transactional(rollbackFor = Exception.class)
    @PreAuthorize("hasAnyAuthority('sys:repairList:dispatch')")
    public ResultVo dispatchOrder(@RequestBody DispatchParm parm) {
        QueryWrapper<DispatchOrder> query = new QueryWrapper<>();
        query.lambda().eq(DispatchOrder::getOrderSource, parm.getRepairId());
        DispatchOrder dispatchOrder = dispatchOrderService.getOne(query);
        UserRepair repair = userRepairService.getById(parm.getRepairId());
        if (dispatchOrder == null) {
            DispatchOrder dispatch = new DispatchOrder();
            dispatch.setUserId(repair.getUserId());
            dispatch.setDispatchNum("D" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + CommonUtils.getRandomCode(4));
            dispatch.setDispatchUserId(parm.getDispatchUserId());
            dispatch.setDispatchTime(new Date());
            dispatch.setStatus("0");
            dispatch.setOrderSource(parm.getRepairId());
            dispatch.setType("0");
            dispatch.setRemark(parm.getRemark());
            dispatch.setOrderId(repair.getOrderNum());
            dispatch.setOperator(userService.getById(parm.getUserId()).getLoginName());
            dispatchOrderService.save(dispatch);
            repair.setStatus("1");
            userRepairService.updateById(repair);
        } else {
            dispatchOrder.setDispatchUserId(parm.getDispatchUserId());
            dispatchOrder.setOperator(userService.getById(parm.getUserId()).getLoginName());
            dispatchOrderService.updateById(dispatchOrder);
        }
        return ResultUtils.success("派工成功");
    }
}
