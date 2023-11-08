package com.jack.web.fee_park.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("fee_park")
public class FeePark implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long parkFeeId;
    private Long userId;
    private Long parkId;
    private String payParkMonth;
    private BigDecimal payParkMoney;
    private String payParkStatus;
    private Date payParkTime;

    @TableField(exist = false)
    private String loginName;

    @TableField(exist = false)
    private String phone;

    @TableField(exist = false)
    private String parkName;

    @TableField(exist = false)
    private String parkType;
}
