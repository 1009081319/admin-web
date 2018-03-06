package com.ly.fn.admin.modules.system.service.impl;

import com.ly.fn.admin.common.exception.ServiceException;
import com.ly.fn.admin.common.page.PageInfo;
import com.ly.fn.admin.modules.system.dao.UserDao;
import com.ly.fn.admin.modules.system.domain.User;
import com.ly.fn.admin.modules.system.domain.UserHasRole;
import com.ly.fn.admin.modules.system.service.UserHasRoleService;
import com.ly.fn.admin.modules.system.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @package：com.ly.fn.admin.modules.system.service.impl
 * @des：
 * @autor ：王兵【wb38113】
 * @createTime： 2017/12/29-15:51
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;
    @Resource
    private UserHasRoleService userHasRoleService;

    @Override

    public User getById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public User getByLoginName(String loginName) {
        return userDao.getByLoginName(loginName);
    }

    @Override
    public List<User> findList(User user) {
        return userDao.findList(user);
    }

    @Override
    public PageInfo findPageList(User user) {
        List<User> userList = findList(user);
        return new PageInfo(user.getDraw(), userDao.getCount(user), userList);
    }

    @Transactional
    @Override
    public User addUser(User user) {
        try {
            userDao.addUser(user);
            //保存角色
            UserHasRole userHasRole = new UserHasRole();
            userHasRole.setUser(user);
            userHasRole.setRole(user.getRole());
            userHasRoleService.addUserHasRole(userHasRole);
        } catch (Exception e) {
            throw new ServiceException("保存用户信息异常", e);
        }
        return getById(user.getId());
    }

    @Transactional
    @Override
    public User updateUser(User user) {
        try {
            userDao.updateUser(user);
        } catch (Exception e) {
            throw new ServiceException("更新用户异常", e);
        }
        return getById(user.getId());
    }
}
