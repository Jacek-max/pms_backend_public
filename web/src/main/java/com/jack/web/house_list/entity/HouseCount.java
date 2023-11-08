package com.jack.web.house_list.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class HouseCount implements Serializable {
    private int houseCount;
    private int houseFree;
    private double houseUsage;
}
