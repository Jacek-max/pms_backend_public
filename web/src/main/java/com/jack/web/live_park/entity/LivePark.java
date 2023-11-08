package com.jack.web.live_park.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("live_park")
public class LivePark implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long liveParkId;
    private Long userId;
    private Long parkId;
    private String liveStatue;
}
