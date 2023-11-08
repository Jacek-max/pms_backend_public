package com.jack.web.menu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@TableName("sys_menu")
public class Menu implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long menuId;
    private Long parentId;
    private String menuLabel;
    private String menuCode;
    private String name;
    private String path;
    private String url;
    private String type;
    private String icon;
    private String remark;
    private String parentName;
    private Integer orderNum;

    //不属于数据库表中的字段，需要排除
    @TableField(exist = false)
    private List<Menu> children = new ArrayList<>();
    @TableField(exist = false)
    private Boolean open;
}
