package com.ly.fn.admin.modules.system.dao;


import com.ly.fn.admin.common.annotation.MyBatisDao;
import com.ly.fn.admin.modules.system.domain.Role;

import java.util.List;

@MyBatisDao
public interface RoleDao {

    Role getById(Long id);

    int getCount(Role role);

    List<Role> findList(Role role);

    int addRole(Role role);

    int updateRole(Role role);

    int deleteById(Long id);
}
