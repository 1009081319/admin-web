package com.ly.fn.admin.modules.system.service;


import com.ly.fn.admin.modules.system.domain.Permission;

import java.util.List;

public interface PermissionService {

    Permission getById(Long id);

    Permission addPermission(Permission permission);

    Permission updatePermission(Permission permission);

    boolean deleteById(Long id);

    List<Permission> findPermissionList(Long userId);
}
