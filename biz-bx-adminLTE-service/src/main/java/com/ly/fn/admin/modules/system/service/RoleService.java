package com.ly.fn.admin.modules.system.service;


import com.ly.fn.admin.common.page.PageInfo;
import com.ly.fn.admin.modules.system.domain.Role;
import com.ly.fn.admin.modules.system.domain.RoleHasPermission;

import java.util.List;

public interface RoleService {

    Role getById(Long id);

    List<Role> findList(Role role);

    PageInfo<Role> findPageList(Role role);

    Role addRole(Role role);

    Role updateRole(Role role);

    boolean deleteById(Long id);

    List<RoleHasPermission> getPermissionByRoleId(Long roleId);

    boolean addRolePermission(Long roleId, List<Long> permissionIdList);
}
