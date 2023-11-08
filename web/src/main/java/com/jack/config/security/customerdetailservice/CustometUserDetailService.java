package com.jack.config.security.customerdetailservice;

import com.jack.web.live_user.entity.LiveUser;
import com.jack.web.live_user.service.LiveUserService;
import com.jack.web.menu.entity.Menu;
import com.jack.web.menu.service.MenuService;
import com.jack.web.user.entity.User;
import com.jack.web.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("custometUserDetailService")
public class CustometUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private LiveUserService liveUserService;

    @Autowired
    private MenuService menuService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        int index = s.indexOf(":");
        String username = s.substring(0, index);
        //用户类型
        String userType = s.substring(index + 1, s.length());

//        UserDetails user = null;
        if (userType.equals("0")) {

            LiveUser liveUser = liveUserService.loadUser(username);
            if (liveUser == null) {
                throw new UsernameNotFoundException("用户账号不存在");
            }

            //查询业主权限信息
            List<Menu> menuList = menuService.getMenuByUserIdForLiveUser(liveUser.getUserId());
            //获取权限字段
            List<String> collect = menuList.stream().filter(item -> item != null).map(item -> item.getMenuCode())
                    .filter(item -> item != null).collect(Collectors.toList());

            //转成数组
            String[] strings = collect.toArray(new String[collect.size()]);
            List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(strings);

            //设置用户权限
            liveUser.setAuthorities(authorityList);
            return liveUser;

        } else if (userType.equals("1")) {

            User user = userService.loadUser(username);
            if (user == null) {
                throw new UsernameNotFoundException("用户账号不存在");
            }
            if (user.getIsUsed().equals("1")) {
                throw new DisabledException("账号已被禁用");
            }

            //查询用户权限信息
            List<Menu> menuList = menuService.getMenuByUserId(user.getUserId());

            //获取权限字段
            List<String> collect = menuList.stream().filter(item -> item != null).map(item -> item.getMenuCode())
                    .filter(item -> item != null).collect(Collectors.toList());

            //转成数组
            String[] strings = collect.toArray(new String[collect.size()]);
            List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(strings);

            //设置用户权限
            user.setAuthorities(authorityList);
            return user;

        } else {
            throw new UsernameNotFoundException("用户类型不存在");
        }


    }
}
