package com.jack.config.security.filter;

import com.jack.config.jwt.JwtUtils;
import com.jack.config.security.customerdetailservice.CustometUserDetailService;
import com.jack.config.security.exception.CustomerAuthenionException;
import com.jack.config.security.handler.LoginFailureHandler;
import io.jsonwebtoken.Claims;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * token验证过滤器
 */
@Data
@Component("checkTokenFilter")
public class CheckTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CustometUserDetailService custometUserDetailService;

    @Autowired
    private LoginFailureHandler loginFailureHandler;

    @Value("${jack.loginUrl}")
    private String loginUrl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String url = request.getRequestURI();
            if (!url.equals(loginUrl)) {
                validateToken(request);
            }
        } catch (AuthenticationException e) {
            loginFailureHandler.commence(request, response, e);
            return;
        }
        filterChain.doFilter(request, response);
    }

    //token验证
    private void validateToken(HttpServletRequest request) {

        String token = request.getHeader("token");

        if (StringUtils.isEmpty(token)) {
            token = request.getParameter("token");
        }

        //如果没有获取到token
        if (StringUtils.isEmpty(token)) {
            throw new CustomerAuthenionException("登录过期，请重新登录！");
        }

        //解析token
        String username = jwtUtils.getUsernameFromToken(token);
        if (StringUtils.isEmpty(username)) {
            throw new CustomerAuthenionException("登录过期，请重新登录！");
        }

        Claims claims = jwtUtils.getClaimsFromToken(token);
        String userType = (String) claims.get("userType");

        //查询用户信息
        UserDetails details = custometUserDetailService.loadUserByUsername(username + ":" + userType);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(details,null,details.getAuthorities());

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

    }
}
