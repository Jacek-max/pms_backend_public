package com.jack.web.user_repair.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class DispatchParm implements Serializable {

    private Long repairId;

    private Long dispatchUserId;

    private String remark;

    private Long userId;

}
