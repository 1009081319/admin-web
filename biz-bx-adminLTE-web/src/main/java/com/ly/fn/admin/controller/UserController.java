package com.ly.fn.admin.controller;

import com.ly.fn.admin.common.page.PageInfo;
import com.ly.fn.admin.modules.system.domain.Role;
import com.ly.fn.admin.modules.system.domain.User;
import com.ly.fn.admin.modules.system.service.RoleService;
import com.ly.fn.admin.modules.system.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RequestMapping(value = "userManage")
@Controller
public class UserController extends BaseController{

    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;

    @RequestMapping(value = "list")
    public String userList(Model model) {
        //获取角色
        List<Role> roleList = roleService.findList(new Role());
        model.addAttribute("roleList", roleList);
        return "/system/userList";
    }

    @RequestMapping(value = "findPageList")
    @ResponseBody
    public PageInfo findPageList(HttpServletRequest request, User user) {
        user.setSortStr(getSortStr(request));
        return userService.findPageList(user);
    }

    @RequestMapping(value = "getById")
    @ResponseBody
    public User getById(Long id) {
        return userService.getById(id);
    }

    @RequestMapping(value = "maintainUser")
    @ResponseBody
    public boolean maintainUser(User user) {
        try {
            if (user.getId() == null) {
                userService.addUser(user);
            } else {
                userService.updateUser(user);
            }
        } catch (Exception e) {
            logger.error("维护用户信息异常:" + e.getMessage(), e);
            return false;
        }
        return true;
    }

}
