package com.jack.web.house_list.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("house_list")
public class HouseList implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long houseId;
    private Long unitId;
    private String houseNum;
    private String houseArea;
    // 使用状态，0：未使用 1：使用
    private String status;

    @TableField(exist = false)
    private Long buildId;
    @TableField(exist = false)
    private String name;
    @TableField(exist = false)
    private String unitName;
}
