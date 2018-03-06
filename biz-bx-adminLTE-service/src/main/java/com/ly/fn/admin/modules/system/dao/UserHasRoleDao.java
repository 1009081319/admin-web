package com.ly.fn.admin.modules.system.dao;


import com.ly.fn.admin.common.annotation.MyBatisDao;
import com.ly.fn.admin.modules.system.domain.UserHasRole;

@MyBatisDao
public interface UserHasRoleDao {

    UserHasRole getById(Long id);

    int addUserHasRole(UserHasRole userHasRole);

    UserHasRole getByUser(Long userId);

    int deleteByUser(Long userId);
}
