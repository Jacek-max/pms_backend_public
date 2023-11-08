package com.jack.web.live_user.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class LiveUserParm implements Serializable {

    private Long pageSize;
    private Long currentPage;
    private String loginName;
    private String phone;
}
