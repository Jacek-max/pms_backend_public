package com.jack.web.house_unit.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class HouseUnitParm implements Serializable {
    private String buildName;
    private String unitName;
    private Long currentPage;
    private Long pageSize;
}
