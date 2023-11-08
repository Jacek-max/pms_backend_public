package com.jack.web.user_repair.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("user_repair")
public class UserRepair implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long repairId;
    private Long userId;
    private String phone;
    private String repairAddress;
    private String repairContent;
    private Date commitTime;
    private String status;
    private String orderNum;

    @TableField(exist = false)
    private String dispatchName;

    @TableField(exist = false)
    private String dispatchNum;
}
