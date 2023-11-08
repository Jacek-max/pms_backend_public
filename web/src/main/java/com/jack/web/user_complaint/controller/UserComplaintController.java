package com.jack.web.user_complaint.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jack.utils.ResultUtils;
import com.jack.utils.ResultVo;
import com.jack.web.user_complaint.entity.UserComplaint;
import com.jack.web.user_complaint.entity.UserComplaintParm;
import com.jack.web.user_complaint.service.UserComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/userComplaint")
public class UserComplaintController {

    @Autowired
    private UserComplaintService userComplaintService;

    @GetMapping("/list")
    public ResultVo getList(UserComplaintParm parm) {
        QueryWrapper<UserComplaint> query = new QueryWrapper<>();
        query.lambda().like(UserComplaint::getTitle, parm.getTitle())
                .like(UserComplaint::getComplaintContent, parm.getComplaintContent());

        IPage<UserComplaint> page = new Page<>();
        page.setCurrent(parm.getCurrentPage());
        page.setSize(parm.getPageSize());
        IPage<UserComplaint> list = userComplaintService.page(page, query);
        return ResultUtils.success("查询成功", list);
    }

    //我的投诉
    @GetMapping("/myList")
    public ResultVo getMyList(UserComplaintParm parm) {
        QueryWrapper<UserComplaint> query = new QueryWrapper<>();
        query.lambda().like(UserComplaint::getTitle, parm.getTitle())
                .like(UserComplaint::getComplaintContent, parm.getComplaintContent())
                .eq(UserComplaint::getUserId,parm.getUserId());

        IPage<UserComplaint> page = new Page<>();
        page.setCurrent(parm.getCurrentPage());
        page.setSize(parm.getPageSize());
        IPage<UserComplaint> list = userComplaintService.page(page, query);
        return ResultUtils.success("查询成功", list);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('sys:userComplaintList:add')")
    public ResultVo add(@RequestBody UserComplaint userComplaint){
        userComplaint.setStatus("0");
        userComplaint.setCreateTime(new Date());
        boolean save = userComplaintService.save(userComplaint);
        if (save){
            return ResultUtils.success("投诉成功");
        }
        return ResultUtils.error("投诉失败!");
    }

    @PutMapping
    @PreAuthorize("hasAuthority('sys:userComplaintList:edit')"+"||hasAuthority('sys:userComplaintList:do')")
    public ResultVo edit(@RequestBody UserComplaint userComplaint){
        boolean b = userComplaintService.updateById(userComplaint);
        if (b){
            return ResultUtils.success("编辑投诉成功");
        }
        return ResultUtils.success("编辑投诉失败!");
    }

    @DeleteMapping("/{complaintId}")
    @PreAuthorize("hasAuthority('sys:userComplaintList:delete')")
    public ResultVo delete(@PathVariable("complaintId") Long complaintId){
        boolean b = userComplaintService.removeById(complaintId);
        if (b){
            return ResultUtils.success("删除投诉成功");
        }
        return ResultUtils.error("删除投诉失败!");
    }

    //获取待受理数量
    @GetMapping("/getWaitingComplaint")
    public ResultVo getWaitingComplaint(){
        QueryWrapper<UserComplaint> query = new QueryWrapper<>();
        query.lambda().eq(UserComplaint::getStatus,"0");
        int count = userComplaintService.count(query);
        return ResultUtils.success("查询成功",count);
    }

    //获取我的未处理投诉数量
    @GetMapping("/getMyComplaintNo")
    public ResultVo getMyComplaintNo(UserComplaintParm parm){
        QueryWrapper<UserComplaint> query = new QueryWrapper<>();
        query.lambda().eq(UserComplaint::getUserId,parm.getUserId())
                .eq(UserComplaint::getStatus,"0");
        int count = userComplaintService.count(query);
        return ResultUtils.success("查询成功",count);
    }
}
