package com.ly.fn.admin.modules.system.service.impl;

import com.ly.fn.admin.common.exception.ServiceException;
import com.ly.fn.admin.modules.system.dao.UserHasRoleDao;
import com.ly.fn.admin.modules.system.domain.UserHasRole;
import com.ly.fn.admin.modules.system.service.UserHasRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service("userHasRoleService")
public class UserHasRoleServiceImpl implements UserHasRoleService {
    @Resource
    private UserHasRoleDao userHasRoleDao;

    @Override
    public UserHasRole getById(Long id) {
        return userHasRoleDao.getById(id);
    }

    @Override
    @Transactional
    public UserHasRole addUserHasRole(UserHasRole userHasRole) {
        try {
            UserHasRole oldRole = getByUser(userHasRole.getUser().getId());
            if (oldRole !=null && !oldRole.getRole().getId().equals(userHasRole.getRole().getId())) {
                //删除其他角色
                deleteByUser(userHasRole.getUser().getId());
            }
            //添加新角色
            userHasRoleDao.addUserHasRole(userHasRole);
        } catch (Exception e) {
            throw new ServiceException("添加用户角色异常", e);
        }
        return getById(userHasRole.getId());
    }

    @Override
    public UserHasRole getByUser(Long userId) {
        return userHasRoleDao.getByUser(userId);
    }

    @Override
    @Transactional
    public boolean deleteByUser(Long userId) {
        try {
            userHasRoleDao.deleteByUser(userId);
        } catch (Exception e) {
            throw new ServiceException("删除用户角色异常", e);
        }
        return true;
    }
}
