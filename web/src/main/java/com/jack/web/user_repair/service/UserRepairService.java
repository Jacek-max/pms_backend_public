package com.jack.web.user_repair.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jack.web.user_repair.entity.UserRepair;
import com.jack.web.user_repair.entity.UserRepairParm;

public interface UserRepairService extends IService<UserRepair> {

    IPage<UserRepair> getList(UserRepairParm parm);

    IPage<UserRepair> getListById(UserRepairParm parm);
}
