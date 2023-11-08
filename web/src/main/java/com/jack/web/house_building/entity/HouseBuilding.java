package com.jack.web.house_building.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("house_building")
public class HouseBuilding implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long buildId;

    private String type;

    private String name;

    private Long orderNum;
}
