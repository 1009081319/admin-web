package com.ly.fn.admin.modules.system.service.impl;

import com.ly.fn.admin.common.exception.ServiceException;
import com.ly.fn.admin.common.page.PageInfo;
import com.ly.fn.admin.modules.system.dao.RoleDao;
import com.ly.fn.admin.modules.system.dao.RoleHasPermissionDao;
import com.ly.fn.admin.modules.system.domain.Role;
import com.ly.fn.admin.modules.system.domain.RoleHasPermission;
import com.ly.fn.admin.modules.system.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleDao roleDao;
    @Resource
    private RoleHasPermissionDao roleHasPermissionDao;

    @Override
    public Role getById(Long id) {
        return roleDao.getById(id);
    }

    @Override
    public List<Role> findList(Role role) {
        return roleDao.findList(role);
    }

    @Override
    public PageInfo<Role> findPageList(Role role) {
        List<Role> roleList = roleDao.findList(role);
        return new PageInfo(role.getDraw(),roleDao.getCount(role),roleList);
    }

    @Transactional
    @Override
    public Role addRole(Role role) {
        try {
            roleDao.addRole(role);
        } catch (Exception e) {
            throw new ServiceException("新增角色异常", e);
        }
        return getById(role.getId());
    }

    @Transactional
    @Override
    public Role updateRole(Role role) {
        try {
            roleDao.updateRole(role);
        } catch (Exception e) {
            throw new ServiceException("修改角色异常",e);
        }
        return getById(role.getId());
    }

    @Transactional
    @Override
    public boolean deleteById(Long id) {
        try {
            roleDao.deleteById(id);
        } catch (Exception e) {
            throw new ServiceException("删除角色异常");
        }
        return true;
    }

    @Override
    public List<RoleHasPermission> getPermissionByRoleId(Long roleId) {
        if (null == roleId) {
            return null;
        }
        RoleHasPermission roleHasPermission = new RoleHasPermission();
        roleHasPermission.setRoleId(roleId);
        return roleHasPermissionDao.findList(roleHasPermission);
    }

    @Override
    @Transactional
    public boolean addRolePermission(Long roleId, List<Long> permissionIdList) {
        try {
            //先删除当前角色下绑定的权限
            roleHasPermissionDao.deleteByRoleId(roleId);
            //重新绑定新的权限
            if (permissionIdList != null && !permissionIdList.isEmpty()) {
                for (Long permissionId : permissionIdList) {
                    RoleHasPermission roleHasPermission = new RoleHasPermission();
                    roleHasPermission.setRoleId(roleId);
                    roleHasPermission.setPermissionId(permissionId);
                    roleHasPermissionDao.addRoleHasPermission(roleHasPermission);
                }
            }
        } catch (Exception e) {
            throw new ServiceException("修改角色权限异常", e);
        }
        return true;
    }
}
