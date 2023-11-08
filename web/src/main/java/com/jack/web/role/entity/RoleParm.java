package com.jack.web.role.entity;

import lombok.Data;

@Data
public class RoleParm {

    private Long pageSize;
    private Long currentPage;
    private String roleName;
}
