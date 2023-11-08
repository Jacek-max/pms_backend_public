package com.jack.web.user_repair.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jack.web.user_repair.entity.UserRepair;
import com.jack.web.user_repair.entity.UserRepairParm;
import com.jack.web.user_repair.mapper.UserRepairMapper;
import com.jack.web.user_repair.service.UserRepairService;
import org.springframework.stereotype.Service;

@Service
public class UserRepairServiceImpl extends ServiceImpl<UserRepairMapper, UserRepair> implements UserRepairService {

    @Override
    public IPage<UserRepair> getList(UserRepairParm parm) {
        IPage<UserRepair> page = new Page<>();
        page.setSize(parm.getPageSize());
        page.setCurrent(parm.getCurrentPage());
        return this.baseMapper.getList(page,parm);
    }

    @Override
    public IPage<UserRepair> getListById(UserRepairParm parm) {
        IPage<UserRepair> page = new Page<>();
        page.setSize(parm.getPageSize());
        page.setCurrent(parm.getCurrentPage());
        return this.baseMapper.getListById(page,parm);
    }

}
