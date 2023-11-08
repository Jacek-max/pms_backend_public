package com.jack.web.sys_notice.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysNoticeParm implements Serializable {

    private Long currentPage;
    private Long pageSize;
    private String title;
}
