package com.jack.web.sys_notice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jack.utils.ResultUtils;
import com.jack.utils.ResultVo;
import com.jack.web.sys_notice.entity.SysNotice;
import com.jack.web.sys_notice.entity.SysNoticeParm;
import com.jack.web.sys_notice.service.SysNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sysNotice")
public class SysNoticeController {

    @Autowired
    private SysNoticeService sysNoticeService;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('sys:noticeList:look')")
    public ResultVo getList(SysNoticeParm parm) {
        QueryWrapper<SysNotice> query = new QueryWrapper<>();
        query.lambda().like(SysNotice::getTitle, parm.getTitle())
                .orderByDesc(SysNotice::getCreateTime);

        IPage<SysNotice> page = new Page<>();
        page.setSize(parm.getPageSize());
        page.setCurrent(parm.getCurrentPage());
        IPage<SysNotice> list = sysNoticeService.page(page, query);
        List<SysNotice> collect = list.getRecords().stream().sorted(Comparator.comparing(SysNotice::getIsTop).reversed()).collect(Collectors.toList());
        list.setRecords(collect);

        return ResultUtils.success("查询成功", list);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('sys:noticeList:add')")
    public ResultVo add(@RequestBody SysNotice sysNotice) {
        sysNotice.setCreateTime(new Date());
        boolean save = sysNoticeService.save(sysNotice);
        if (save) {
            return ResultUtils.success("新增公告成功");
        }
        return ResultUtils.error("新增公告失败！");
    }

    @PutMapping
    @PreAuthorize("hasAuthority('sys:noticeList:edit')")
    public ResultVo edit(@RequestBody SysNotice sysNotice) {
        boolean b = sysNoticeService.updateById(sysNotice);
        if (b) {
            return ResultUtils.success("编辑公告成功");
        }
        return ResultUtils.error("编辑公告失败！");
    }

    @DeleteMapping("/{noticeId}")
    @PreAuthorize("hasAuthority('sys:noticeList:delete')")
    public ResultVo delete(@PathVariable("noticeId") Long noticeId) {
        boolean b = sysNoticeService.removeById(noticeId);
        if (b) {
            return ResultUtils.success("删除公告成功");
        }
        return ResultUtils.error("删除公告失败！");
    }

    @GetMapping("/isTop")
    @PreAuthorize("hasAuthority('sys:noticeList:top')")
    public ResultVo isTop(SysNotice sysNotice) {
        System.out.println(sysNotice.getNoticeId());
        SysNotice notice = sysNoticeService.getById(sysNotice.getNoticeId());
        if (notice.getIsTop().equals("0")) {
            notice.setIsTop("1");
        } else {
            notice.setIsTop("0");
        }
        boolean b = sysNoticeService.updateById(notice);
        if (b) {
            return ResultUtils.success("操作成功");
        }
        return ResultUtils.error("操作失败");
    }

    @GetMapping("/getTop")
    public ResultVo getTop(){
        QueryWrapper<SysNotice> query = new QueryWrapper<>();
        query.lambda().eq(SysNotice::getIsTop,"1").orderByDesc(SysNotice::getCreateTime);
        return ResultUtils.success("",sysNoticeService.list(query));
    }
}
