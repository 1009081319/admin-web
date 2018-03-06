package com.ly.fn.admin.controller;


import com.ly.fn.admin.modules.system.domain.Permission;
import com.ly.fn.admin.modules.system.service.PermissionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@RequestMapping(value = "permissionManage")
@Controller
public class PermissionController extends BaseController {

    @Resource
    private PermissionService permissionService;

    @RequestMapping(value = "list")
    public String list() {
        return "/system/permissionList";
    }

    @RequestMapping(value = "addPermission")
    @ResponseBody
    public Permission addPermission(Permission permission) {
        try {
            permission = permissionService.addPermission(permission);
        } catch (Exception e) {
            logger.info("添加权限异常:", e);
        }
        return permission;
    }

    @RequestMapping(value = "updatePermission")
    @ResponseBody
    public Permission updatePermission(Permission permission) {
        try {
            permission = permissionService.updatePermission(permission);
        } catch (Exception e) {
            logger.error("更新权限异常", e);
        }
        return permission;
    }

    @RequestMapping(value = "deleteById")
    @ResponseBody
    public boolean deleteById(Long id) {
        try {
            return permissionService.deleteById(id);
        } catch (Exception e) {
            logger.error("删除权限异常", e);
            return false;
        }
    }

    @RequestMapping(value = "getById")
    @ResponseBody
    public Permission getById(Long id) {
        return permissionService.getById(id);
    }

}
