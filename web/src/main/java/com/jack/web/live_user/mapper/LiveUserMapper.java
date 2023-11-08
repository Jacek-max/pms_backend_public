package com.jack.web.live_user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jack.web.live_user.entity.LiveUser;
import org.apache.ibatis.annotations.Param;

public interface LiveUserMapper extends BaseMapper<LiveUser> {

    IPage<LiveUser> getList(IPage<LiveUser> page,
                            @Param("loginName") String loginName, @Param("phone") String phone);

    LiveUser getUser(@Param("userId") Long userId);
}
