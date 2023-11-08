package com.jack.web.user.entity;

import lombok.Data;

@Data
public class UserParm {

    private Long pageSize;
    private Long currentPage;
    private String loginName;
    private String phone;

}
