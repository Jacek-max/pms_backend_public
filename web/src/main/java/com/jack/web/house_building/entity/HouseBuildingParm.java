package com.jack.web.house_building.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class HouseBuildingParm implements Serializable {
    private String name;
    private String type;
    private Long pageSize;
    private Long currentPage;
}
