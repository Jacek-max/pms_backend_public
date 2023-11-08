package com.jack.web.user_complaint.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserComplaintParm implements Serializable {

    private Long currentPage;
    private Long pageSize;
    private Long userId;
    private String title;
    private String complaintContent;
}
