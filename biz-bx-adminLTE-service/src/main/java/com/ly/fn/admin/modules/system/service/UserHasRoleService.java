package com.ly.fn.admin.modules.system.service;

import com.ly.fn.admin.modules.system.domain.UserHasRole;


public interface UserHasRoleService {

    UserHasRole getById(Long id);

    UserHasRole addUserHasRole(UserHasRole userHasRole);

    UserHasRole getByUser(Long userId);

    boolean deleteByUser(Long userId);
}
