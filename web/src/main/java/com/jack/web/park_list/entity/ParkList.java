package com.jack.web.park_list.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("parking_list")
public class ParkList implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long parkId;
    private String parkType;
    private String parkName;
    private String parkStatus;
    private Long parkNum;
}
