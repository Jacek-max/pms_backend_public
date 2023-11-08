package com.jack.web.house_list.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ListParm implements Serializable {
    private String buildName;
    private String status;
    private String unitName;
    private String houseNum;
    private Long currentPage;
    private Long pageSize;
}
