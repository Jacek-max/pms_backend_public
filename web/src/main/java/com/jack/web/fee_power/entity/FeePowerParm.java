package com.jack.web.fee_power.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class FeePowerParm implements Serializable {
    private Long currentPage;
    private Long pageSize;
    private String userName;
    private String houseNum;
    private Long userId;
}
