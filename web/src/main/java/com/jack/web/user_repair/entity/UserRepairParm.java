package com.jack.web.user_repair.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRepairParm implements Serializable {
    private Long currentPage;
    private Long pageSize;
    private Long userId;
    private String repairContent;
}
