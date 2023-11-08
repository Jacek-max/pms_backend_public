package com.jack.web.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jack.web.user.entity.User;
import com.jack.web.user.entity.UserParm;
import com.jack.web.user_role.entity.UserRole;

public interface UserService extends IService<User> {

    IPage<User> list(UserParm parm);

    UserRole getRoleByUserId(UserRole userRole);

    void saveRole(UserRole userRole);

    //根据登录名查用户
    User loadUser(String username);
}
