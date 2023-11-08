package com.jack.web.user_role.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("sys_user_role")
public class UserRole implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long userRoleId;
    private Long roleId;
    private Long userId;

}
