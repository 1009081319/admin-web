package com.ly.fn.admin.modules.system.service;

import com.ly.fn.admin.common.page.PageInfo;
import com.ly.fn.admin.modules.system.domain.User;

import java.util.List;

/**
 * @package：com.ly.fn.admin.modules.system.service
 * @des：
 * @autor ：王兵【wb38113】
 * @createTime： 2017/12/29-15:50
 */
public interface UserService {

    User getById(Long id);

    User getByLoginName(String loginName);

    List<User> findList(User user);

    PageInfo findPageList(User user);

    User addUser(User user);

    User updateUser(User user);

}
