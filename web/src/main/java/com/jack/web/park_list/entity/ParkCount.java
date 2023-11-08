package com.jack.web.park_list.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ParkCount implements Serializable {
    private int parkCount;
    private int parkFree;
    private double parkUsage;
}
