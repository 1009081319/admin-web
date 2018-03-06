package com.ly.fn.admin.modules.system.dao;

import com.ly.fn.admin.common.annotation.MyBatisDao;
import com.ly.fn.admin.modules.system.domain.RoleHasPermission;

import java.util.List;

@MyBatisDao
public interface RoleHasPermissionDao {

    int addRoleHasPermission(RoleHasPermission roleHasPermission);

    List<RoleHasPermission> findList(RoleHasPermission roleHasPermission);

    int deleteById(Long id);

    int deleteByRoleId(Long roleId);
}
