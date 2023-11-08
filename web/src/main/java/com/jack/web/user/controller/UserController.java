package com.jack.web.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jack.config.jwt.JwtUtils;
import com.jack.utils.ResultUtils;
import com.jack.utils.ResultVo;
import com.jack.web.live_user.entity.LiveUser;
import com.jack.web.live_user.service.LiveUserService;
import com.jack.web.menu.entity.MakeMenuTree;
import com.jack.web.menu.entity.Menu;
import com.jack.web.menu.entity.RouterVO;
import com.jack.web.menu.service.MenuService;
import com.jack.web.user.entity.*;
import com.jack.web.user.service.UserService;
import com.jack.web.user_role.entity.UserRole;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private JwtUtils jwtUtils;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private LiveUserService liveUserService;

    //获取路由列表
    @GetMapping("/getMenuList")
    public ResultVo getMenuList(HttpServletRequest request) {
        //获取token
        String token = request.getHeader("token");
        String username = jwtUtils.getUsernameFromToken(token);
        Claims claims = jwtUtils.getClaimsFromToken(token);
        Object userType = claims.get("userType");

        if (userType.equals("0")) {
            LiveUser liveUser = liveUserService.loadUser(username);
            //查询业主权限信息
            List<Menu> menuList = menuService.getMenuByUserIdForLiveUser(liveUser.getUserId());
            List<Menu> collect = menuList.stream().filter(item -> item != null && !item.getType().equals("2"))
                    .collect(Collectors.toList());
            //组装路由数据
            List<RouterVO> routerVOS = MakeMenuTree.makeRouter(collect, 0L);
            return ResultUtils.success("查询成功", routerVOS);

        } else {

            User user = userService.loadUser(username);
            //查询业主权限信息
            List<Menu> menuList = menuService.getMenuByUserId(user.getUserId());
            List<Menu> collect = menuList.stream().filter(item -> item != null && !item.getType().equals("2"))
                    .collect(Collectors.toList());
            //组装路由数据
            List<RouterVO> routerVOS = MakeMenuTree.makeRouter(collect, 0L);
            return ResultUtils.success("查询成功", routerVOS);

        }
    }

    //获取用户信息
    @GetMapping("/getInfo")
    public ResultVo getInfo(User user, HttpServletRequest request) {
        String token = request.getHeader("token");
        Claims claims = jwtUtils.getClaimsFromToken(token);
        Object userType = claims.get("userType");

        if (userType.equals("0")) {

            LiveUser liveUser = liveUserService.getById(user.getUserId());
            UserInfo userInfo = new UserInfo();
            userInfo.setId(liveUser.getUserId());
            userInfo.setName("欢迎您，" + liveUser.getLoginName());
            userInfo.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
            //查询业主权限信息
            List<Menu> menuList = menuService.getMenuByUserIdForLiveUser(liveUser.getUserId());
            //获取权限字段
            List<String> collect = menuList.stream().filter(item -> item != null).map(item -> item.getMenuCode())
                    .filter(item -> item != null).collect(Collectors.toList());

            //转成数组
            String[] strings = collect.toArray(new String[collect.size()]);
            userInfo.setRoles(strings);
            return ResultUtils.success("获取用户信息成功", userInfo);

        } else {

            //根据用户Id查询
            User user1 = userService.getById(user.getUserId());
            UserInfo userInfo = new UserInfo();
            userInfo.setId(user1.getUserId());
            userInfo.setName(user1.getUsername());
//            userInfo.setIsUser(user1.getIsUsed());
            userInfo.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");

            //查询用户权限信息
            List<Menu> menuList = menuService.getMenuByUserId(user.getUserId());

            //获取权限字段
            List<String> collect = menuList.stream().filter(item -> item != null).map(item -> item.getMenuCode())
                    .filter(item -> item != null).collect(Collectors.toList());

            //转成数组
            String[] strings = collect.toArray(new String[collect.size()]);
            userInfo.setRoles(strings);
            return ResultUtils.success("获取用户信息成功", userInfo);
        }
    }

    @PostMapping("/login")
    public ResultVo login(@RequestBody LoginParm parm) {
        if (StringUtils.isEmpty(parm.getUsername()) || StringUtils.isEmpty(parm.getPassword()) || StringUtils.isEmpty(parm.getUserType())) {
            return ResultUtils.error("用户名、密码和用户类型不能为空!");
        }

        //密码加密
        String encode = passwordEncoder.encode(parm.getPassword());

        //spring security需要的token
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new
                UsernamePasswordAuthenticationToken(parm.getUsername() + ":" + parm.getUserType(), parm.getPassword());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        if (parm.getUserType().equals("0")) {

            LiveUser user = (LiveUser) authenticate.getPrincipal();
            String token = jwtUtils.generateToken(parm.getUsername(), parm.getUserType());
            Long time = jwtUtils.getExpireTime(token);
            LoginResult result = new LoginResult();
            result.setUserId(user.getUserId());
            result.setToken(token);
            result.setExpireTime(time);
            return ResultUtils.success("登录成功", result);

        } else {

            User user = (User) authenticate.getPrincipal();
            String token = jwtUtils.generateToken(parm.getUsername(), parm.getUserType());
            Long time = jwtUtils.getExpireTime(token);
            LoginResult result = new LoginResult();
            result.setUserId(user.getUserId());
            result.setToken(token);
            result.setExpireTime(time);
            return ResultUtils.success("登录成功", result);

        }
//        Object credentials = authenticate.getCredentials();
    }

//    @PostMapping("/login")
//    public ResultVo login(@RequestBody LoginParm parm) {
//        if (StringUtils.isEmpty(parm.getUsername()) || StringUtils.isEmpty(parm.getPassword()) || StringUtils.isEmpty(parm.getUserType())) {
//            return ResultUtils.error("用户名、密码和用户类型不能为空!");
//        }
//        String password = DigestUtils.md5DigestAsHex(parm.getPassword().getBytes());
//        QueryWrapper<User> query = new QueryWrapper<>();
//        query.lambda().eq(User::getLoginName, parm.getUsername())
//                .eq(User::getPassword, password);
//        User user = userService.getOne(query);
//        if (user == null) {
//            return ResultUtils.error("用户名、密码或者用户类型错误");
//        }
//
//        String token = jwtUtils.generateToken(user.getUsername());
//        Long time = jwtUtils.getExpireTime(token);
//        LoginResult result = new LoginResult();
//        result.setUserId(user.getUserId());
//        result.setToken(token);
//        result.setExpireTime(time);
//
//        return ResultUtils.success("登录成功", result);
//
//    }

    @PostMapping
    @PreAuthorize("hasAuthority('sys:user:add')")
    public ResultVo addUser(@RequestBody User user) {

        if (StringUtils.isNotEmpty(user.getUsername())) {
            //校验账号是否唯一
            QueryWrapper<User> query = new QueryWrapper<>();
            query.lambda().eq(User::getUsername, user.getUsername());
            User one = userService.getOne(query);
            if (one != null) {
                return ResultUtils.error("用户名已被占用！！！", 500);
            }
        }

        //密码加密
        if (StringUtils.isNotEmpty(user.getPassword())) {
//            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        boolean save = userService.save(user);
        if (save) {
            return ResultUtils.success("新增员工成功");
        }
        return ResultUtils.error("新增员工失败");

    }

    @PutMapping
    @PreAuthorize("hasAuthority('sys:user:edit')")
    public ResultVo editUser(@RequestBody User user) {

        //校验账号是否唯一
        if (StringUtils.isNotEmpty(user.getUsername())) {
            QueryWrapper<User> query = new QueryWrapper<>();
            query.lambda().eq(User::getUsername, user.getUsername());
            User one = userService.getOne(query);
            if (one != null && one.getUserId() != user.getUserId()) {
                return ResultUtils.error("账号已被占用！！！", 500);
            }
        }

        //密码加密
        if (StringUtils.isNotEmpty(user.getPassword())) {
//            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        boolean b = userService.updateById(user);
        if (b) {
            return ResultUtils.success("修改员工信息成功");
        }
        return ResultUtils.error("修改员工信息失败");

    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('sys:user:delete')")
    public ResultVo deleteUser(@PathVariable("userId") Long userId) {

        boolean b = userService.removeById(userId);
        if (b) {
            return ResultUtils.success("删除员工成功");
        }
        return ResultUtils.error("删除员工失败");
    }

    @GetMapping("/list")
    public ResultVo list(UserParm parm) {
        IPage<User> list = userService.list(parm);
        list.getRecords().stream().forEach(item -> item.setPassword(""));
        return ResultUtils.success("查询成功", list);
    }

    //根据用户Id查询角色
    @GetMapping("/getRoleByUserId")
    public ResultVo getRoleByUserId(UserRole userRole) {
        UserRole userRole1 = userService.getRoleByUserId(userRole);
        return ResultUtils.success("查询成功", userRole1);
    }

    //保存用户角色
    @PreAuthorize("hasAuthority('sys:user:assignRole')")
    @PostMapping("/saveRole")
    public ResultVo saveRole(@RequestBody UserRole userRole) {
        userService.saveRole(userRole);
        return ResultUtils.success("分配角色成功！");
    }

    //退出登录
    @PostMapping("/loginOut")
    public ResultVo loginOut(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return ResultUtils.success("退出登录成功");
    }

    //重置密码
    @PostMapping("/resetPassword")
    public ResultVo resetPassword(@RequestBody ChangePassword user, HttpServletRequest request) {
        String token = request.getHeader("token");
        Claims claims = jwtUtils.getClaimsFromToken(token);
        Object userType = claims.get("userType");
        if (userType.equals("0")) {
            LiveUser liveUser = liveUserService.getById(user.getUserId());
            String dataOldPassword = liveUser.getPassword();
            boolean matches = passwordEncoder.matches(user.getOldPassword(), dataOldPassword);
            if (!matches) {
                return ResultUtils.error("原密码错误！");
            }

            LiveUser liveUser1 = new LiveUser();
            liveUser1.setUserId(user.getUserId());
            liveUser1.setPassword(passwordEncoder.encode(user.getNewPassword()));
            boolean b = liveUserService.updateById(liveUser1);
            if (b) {
                return ResultUtils.success("密码修改成功");
            }
            return ResultUtils.error("密码修改失败！");

        } else {

            User liveUser = userService.getById(user.getUserId());
            String dataOldPassword = liveUser.getPassword();
            String encode = passwordEncoder.encode(user.getOldPassword());
            if (!dataOldPassword.equals(encode)) {
                return ResultUtils.error("原密码错误！");
            }

            User liveUser1 = new User();
            liveUser1.setUserId(user.getUserId());
            liveUser1.setPassword(passwordEncoder.encode(user.getNewPassword()));
            boolean b = userService.updateById(liveUser1);
            if (b) {
                return ResultUtils.success("密码修改成功");
            }
            return ResultUtils.error("密码修改失败！");

        }

    }

    /**
     * 获取员工列表
     *
     * @return
     */
    @GetMapping("/getUserList")
    public ResultVo getUserList() {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.lambda().eq(User::getStatus, "0").eq(User::getIsUsed, "0");
        return ResultUtils.success("", userService.list(query));
    }
}
