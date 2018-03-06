package com.ly.fn.admin.modules.system.dao;

import com.ly.fn.admin.common.annotation.MyBatisDao;
import com.ly.fn.admin.modules.system.domain.User;

import java.util.List;

/**
 * @package：com.ly.fn.admin.modules.system.dao
 * @des：
 * @autor ：王兵【wb38113】
 * @createTime： 2017/12/29-15:48
 */
@MyBatisDao
public interface UserDao {

    User getById(Long id);

    User getByLoginName(String loginName);

    List<User> findList(User user);

    int addUser(User user);

    int updateUser(User user);

    int getCount(User user);
}
