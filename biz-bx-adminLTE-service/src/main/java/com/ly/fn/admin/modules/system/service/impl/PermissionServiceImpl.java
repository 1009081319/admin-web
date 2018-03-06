package com.ly.fn.admin.modules.system.service.impl;

import com.ly.fn.admin.common.exception.ServiceException;
import com.ly.fn.admin.modules.system.dao.PermissionDao;
import com.ly.fn.admin.modules.system.domain.Permission;
import com.ly.fn.admin.modules.system.service.PermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private PermissionDao permissionDao;

    @Override
    public Permission getById(Long id) {
        return permissionDao.getById(id);
    }

    @Override
    @Transactional
    public Permission addPermission(Permission permission) {
        try {
            permissionDao.addPermission(permission);
        } catch (Exception e) {
            throw new ServiceException("添加权限异常", e);
        }
        return getById(permission.getId());
    }

    @Override
    @Transactional
    public Permission updatePermission(Permission permission) {
        try {
            permissionDao.updatePermission(permission);
        } catch (Exception e) {
            throw new ServiceException("修改权限异常", e);
        }
        return getById(permission.getId());
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            permissionDao.deleteById(id);
        } catch (Exception e) {
            throw new ServiceException("删除权限异常", e);
        }
        return true;
    }

    @Override
    public List<Permission> findPermissionList(Long userId) {
        return permissionDao.getPermissionByUserId(userId);
    }
}
