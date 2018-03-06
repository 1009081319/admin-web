package com.ly.fn.admin.common.shiro;

import com.ly.fn.admin.common.util.StringUtils;
import com.ly.fn.admin.modules.system.domain.Permission;
import com.ly.fn.admin.modules.system.domain.User;
import com.ly.fn.admin.modules.system.service.PermissionService;
import com.ly.fn.admin.modules.system.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Date;
import java.util.List;

/**
 * @package：com.ly.fn.admin.common.shiro
 * @des： 自定义shiro认证和授权类
 * @autor ：王兵【wb38113】
 * @createTime： 2018/1/4-11:23
 */
public class AdminShiroRealm extends AuthorizingRealm {

    private UserService userService;

    private PermissionService permissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) getAvailablePrincipal(principalCollection);
        user = userService.getById(user.getId());
        if (null != user) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            List<Permission> permissionList = permissionService.findPermissionList(user.getId());
            //添加权限信息和角色信息
            if (null != permissionList && !permissionList.isEmpty()) {
                for (Permission permission : permissionList) {
                    if (!StringUtils.isEmpty(permission.getEname())) {
                        info.addStringPermission(permission.getEname());
                    }
                }
            }
            info.addRole(user.getRole().getEname());
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String loginName = token.getUsername();
        String password = String.valueOf(token.getPassword());
        if (!StringUtils.isEmpty(loginName)) {
            User user = userService.getByLoginName(loginName);
            if (null != user && user.getPassword().equals(password)) {
                //更新登录信息
                user.setLastLoginTime(new Date());
                userService.updateUser(user);
                return new SimpleAuthenticationInfo(user,user.getPassword(),getName());
            }
        }
        return null;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public PermissionService getPermissionService() {
        return permissionService;
    }

    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }
}
