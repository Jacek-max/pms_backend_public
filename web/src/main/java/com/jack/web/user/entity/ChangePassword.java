package com.jack.web.user.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ChangePassword implements Serializable {

    private Long userId;
    private String oldPassword;
    private String newPassword;
}
