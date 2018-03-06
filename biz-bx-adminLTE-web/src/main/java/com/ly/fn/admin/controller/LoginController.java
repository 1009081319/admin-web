package com.ly.fn.admin.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "")
public class LoginController extends BaseController{

    @RequestMapping(value = "login")
    public String login(String loginName,String password,boolean isRemember) {
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(loginName, password);
            SecurityUtils.getSubject().login(token);
        } catch (AuthenticationException e) {
            logger.error("登录异常", e);
            return "redirect:/";
        }
        return "/system/workbenchList";
    }

}
