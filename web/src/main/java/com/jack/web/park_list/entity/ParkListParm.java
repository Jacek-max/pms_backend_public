package com.jack.web.park_list.entity;

import lombok.Data;

@Data
public class ParkListParm {
    private Long currentPage;
    private Long pageSize;
    private String parkName;
    private String parkStatus;
    private String parkType;
}
