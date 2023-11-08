package com.jack.web.menu.entity;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MakeMenuTree {
    public static List<Menu> makeTree(List<Menu> menuList, Long pid) {
        List<Menu> list = new ArrayList<>();
        Optional.ofNullable(menuList).orElse(new ArrayList<>())
                .stream()
                .filter(item -> item != null && item.getParentId() == pid)
                .forEach(dom -> {
                    Menu menu = new Menu();
                    BeanUtils.copyProperties(dom, menu);
                    List<Menu> menus = makeTree(menuList, dom.getMenuId());
                    menu.setChildren(menus);
                    list.add(menu);
                });
        return list;
    }

    //生成路由的数据格式
    public static List<RouterVO> makeRouter(List<Menu> menuList, Long pid) {
        //接收生产的路由数据
        List<RouterVO> list = new ArrayList<>();

        //组装数据
        Optional.ofNullable(menuList).orElse(new ArrayList<>())
                .stream()
                .filter(item -> item != null && item.getParentId() == pid)
                .forEach(item -> {
                    RouterVO router = new RouterVO();
                    router.setName(item.getName());
                    router.setPath(item.getPath());

                    //判断是否为1级菜单
                    if (item.getParentId() == 0L) {
                        router.setComponent("Layout");
                        router.setAlwaysShow(true);
                    } else {
                        router.setComponent(item.getUrl());
                        router.setAlwaysShow(false);
                    }

                    //设置meta
                    router.setMeta(router.new Meta(
                            item.getMenuLabel(),
                            item.getIcon(),
                            item.getMenuCode().split(",")
                    ));

                    //设置children
                    List<RouterVO> children = makeRouter(menuList, item.getMenuId());
                    router.setChildren(children);
                    list.add(router);
                });
        return list;
    }
}
