package com.jack.web.live_user.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class AssignHouseParm implements Serializable {
    private Long userId;
    private Long houseId;
}
