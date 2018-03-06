package com.ly.fn.admin.modules.system.dao;

import com.ly.fn.admin.common.annotation.MyBatisDao;
import com.ly.fn.admin.modules.system.domain.TreeDomain;

import java.util.List;

@MyBatisDao
public interface SystemDao {

    List<TreeDomain> getPermissionTree(Long pid);
}
