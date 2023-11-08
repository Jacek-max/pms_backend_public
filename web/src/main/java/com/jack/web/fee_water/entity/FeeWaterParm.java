package com.jack.web.fee_water.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class FeeWaterParm implements Serializable {
    private Long currentPage;
    private Long pageSize;
    private String userName;
    private String houseNum;
    private Long userId;
}
