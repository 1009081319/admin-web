package com.ly.fn.admin.modules.system.dao;

import com.ly.fn.admin.common.annotation.MyBatisDao;
import com.ly.fn.admin.modules.system.domain.Permission;

import java.util.List;

/**
 * @package：com.ly.fn.admin.modules.system.dao
 * @des：
 * @autor ：王兵【wb38113】
 * @createTime： 2018/1/9-10:28
 */
@MyBatisDao
public interface PermissionDao {

    Permission getById(Long id);

    int addPermission(Permission permission);

    int updatePermission(Permission permission);

    int deleteById(Long id);

    List<Permission> getPermissionByUserId(Long userId);
}
