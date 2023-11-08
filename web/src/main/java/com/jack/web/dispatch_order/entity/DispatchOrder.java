package com.jack.web.dispatch_order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("dispatch_order")
public class DispatchOrder implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long dispatchId;

    private String type;

    private Long userId;

    private String orderId;

    private Long orderSource;

    private Long dispatchUserId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date dispatchTime;

    private String status;

    private String remark;

    private String operator;

    private String dispatchNum;

    @TableField(exist = false)
    private String phone;
    @TableField(exist = false)
    private String repairAddress;
    @TableField(exist = false)
    private String repairContent;
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date commitTime;

}
