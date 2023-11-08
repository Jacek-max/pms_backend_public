package com.jack.web.sys_notice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jack.web.sys_notice.entity.SysNotice;
import com.jack.web.sys_notice.mapper.SysNoticeMapper;
import com.jack.web.sys_notice.service.SysNoticeService;
import org.springframework.stereotype.Service;

@Service
public class SysNoticeServiceImpl extends ServiceImpl<SysNoticeMapper, SysNotice> implements SysNoticeService {
}
