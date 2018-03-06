package com.ly.fn.admin.controller;

import com.ly.fn.admin.common.page.PageInfo;
import com.ly.fn.admin.common.result.Result;
import com.ly.fn.admin.modules.system.TreeTypeEnum;
import com.ly.fn.admin.modules.system.domain.Role;
import com.ly.fn.admin.modules.system.domain.RoleHasPermission;
import com.ly.fn.admin.modules.system.domain.TreeDomain;
import com.ly.fn.admin.modules.system.service.RoleService;
import com.ly.fn.admin.modules.system.service.SystemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping(value = "roleManage")
public class RoleController extends BaseController {

    @Resource
    private RoleService roleService;
    @Resource
    private SystemService systemService;

    @RequestMapping(value = "list")
    public String list() {
        return "/system/roleList";
    }

    @RequestMapping(value = "findPageList")
    @ResponseBody
    public PageInfo findPageList(HttpServletRequest request, Role role) {
        role.setSortStr(getSortStr(request));
        return roleService.findPageList(role);
    }

    @RequestMapping(value = "addRole")
    @ResponseBody
    public Result addRole(Role role) {
        try {
            role = roleService.addRole(role);
        } catch (Exception e) {
            return new Result();
        }
        Result result = new Result();
        result.setCode("0000");
        result.setMessage("success");
        return result;
    }

    @RequestMapping(value = "getRoleHasPermission")
    @ResponseBody
    public List<TreeDomain> getRoleHasPermission(Long roleId) {
        List<TreeDomain> permissionList = null;
        try {
            //获取整颗权限树
            permissionList = systemService.getTreeList(TreeTypeEnum.PERMISSION.type, null);
            //获取当前角色已经拥有的权限
            List<RoleHasPermission> roleHasPermissionList = roleService.getPermissionByRoleId(roleId);
            if (roleHasPermissionList != null && !roleHasPermissionList.isEmpty()) {
                permissionList = recursionCheckRolePermission(permissionList, roleHasPermissionList);
            }
        } catch (Exception e) {
            logger.error("获取角色拥有权限异常", e);
        }
        return permissionList;
    }

    private List<TreeDomain> recursionCheckRolePermission(List<TreeDomain> permissionList, List<RoleHasPermission> roleHasPermissionList) {
        if (permissionList != null && !permissionList.isEmpty()) {
            for (RoleHasPermission roleHasPermission : roleHasPermissionList) {
                for (TreeDomain treeDomain : permissionList) {
                    if (treeDomain.getId().equals(roleHasPermission.getPermissionId())) {
                        treeDomain.setChecked(true);
                        recursionCheckRolePermission(treeDomain.getChildren(), roleHasPermissionList);
                    }
                }
            }
        }
        return permissionList;
    }

    @RequestMapping(value = "addRolePermission")
    @ResponseBody
    public boolean addRolePermission(Long roleId, List<Long> permissionIds) {
        try {
            roleService.addRolePermission(roleId, permissionIds);
        } catch (Exception e) {
            logger.error("修改角色权限异常", e);
            return false;
        }
        return true;
    }

}
