package com.jack.web.live_house.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("live_house")
public class LiveHouse implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long liveHouseId;
    private Long userId;
    private Long houseId;
    private String useStatus;
}
