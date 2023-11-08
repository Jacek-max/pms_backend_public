package com.jack.web.live_user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

@Data
@TableName("live_user")
public class LiveUser implements UserDetails, Serializable {

    @TableId(type = IdType.AUTO)
    private Long userId;
    //账号
    private String username;
    private String phone;
    private String sex;
    //姓名
    private String loginName;
    private String password;
    private String status;

    @TableField(exist = false)
    private Long roleId;
    @TableField(exist = false)
    private String houseNum;
    @TableField(exist = false)
    private String houseArea;
    @TableField(exist = false)
    private String unitName;
    @TableField(exist = false)
    private String name;
    @TableField(exist = false)
    private String parkName;
    @TableField(exist = false)
    private Long houseId;
    @TableField(exist = false)
    private Long parkId;
    @TableField(exist = false)
    private String useStatus;
    @TableField(exist = false)
    private String liveStatue;

    //spring security的UserDetails的字段
    //帐户是否过期(1 未过期，0已过期)
    private boolean isAccountNonExpired = true;
    //帐户是否被锁定(1 未锁定，0已锁定)
    private boolean isAccountNonLocked = true;
    //密码是否过期(1 未过期，0已过期)
    private boolean isCredentialsNonExpired = true;
    //帐户是否可用(1 可用，0 删除用户)
    private boolean isEnabled = true;
    //由于authorities不是数据库里面的字段，所以要排除他，不然mybatis-plus找不到该字段会报错
    @TableField(exist = false)
    Collection<? extends GrantedAuthority> authorities;


}
