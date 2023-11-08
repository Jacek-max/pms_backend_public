package com.jack.web.fee_water.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("fee_water")
public class FeeWater implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long waterId;
    private Long houseId;
    private Long userId;
    private String payWaterMonth;
    private BigDecimal payWaterMoney;
    private String waterrNum;
    private String payWaterStatus;
    private Date payWaterTime;

    @TableField(exist = false)
    private String loginName;

    @TableField(exist = false)
    private String phone;

    @TableField(exist = false)
    private String houseNum;

    @TableField(exist = false)
    private String unitName;

    @TableField(exist = false)
    private Long buildId;

    @TableField(exist = false)
    private Long unitId;

    @TableField(exist = false)
    private String name;
}
