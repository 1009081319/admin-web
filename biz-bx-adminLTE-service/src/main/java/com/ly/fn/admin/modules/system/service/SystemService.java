package com.ly.fn.admin.modules.system.service;


import com.ly.fn.admin.modules.system.domain.TreeDomain;

import java.util.List;

public interface SystemService {

    List<TreeDomain> getTreeList(String treeCode, Long pid);
}
