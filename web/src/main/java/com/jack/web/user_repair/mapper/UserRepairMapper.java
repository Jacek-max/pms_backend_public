package com.jack.web.user_repair.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jack.web.user_repair.entity.UserRepair;
import com.jack.web.user_repair.entity.UserRepairParm;
import org.apache.ibatis.annotations.Param;

public interface UserRepairMapper extends BaseMapper<UserRepair> {

    IPage<UserRepair> getList(IPage<UserRepair> page, @Param("parm") UserRepairParm parm);

    IPage<UserRepair> getListById(IPage<UserRepair> page, @Param("parm") UserRepairParm parm);
}
