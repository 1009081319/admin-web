package com.ly.fn.admin.modules.system.service.impl;

import com.ly.fn.admin.modules.system.dao.SystemDao;
import com.ly.fn.admin.modules.system.domain.TreeDomain;
import com.ly.fn.admin.modules.system.service.SystemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("systemService")
public class SystemServiceImpl implements SystemService {
    @Resource
    private SystemDao systemDao;

    @Override
    public List<TreeDomain> getTreeList(String treeCode, Long pid) {
        if ("permission".equals(treeCode)) {
            if (null == pid) {
                return recursionChildNode(systemDao.getPermissionTree(0L));
            } else {
                return recursionChildNode(systemDao.getPermissionTree(pid));
            }
        }
        return null;
    }

    private List<TreeDomain> recursionChildNode(List<TreeDomain> list) {
        if (null != list && !list.isEmpty()) {
            for (TreeDomain treeDomain : list) {
                List<TreeDomain> childNodes = systemDao.getPermissionTree(treeDomain.getId());
                if (childNodes != null && !childNodes.isEmpty()) {
                    treeDomain.setChildren(recursionChildNode(childNodes));
                }
            }
        }
        return list;
    }
}
