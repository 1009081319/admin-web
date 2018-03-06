package com.ly.fn.admin.modules.system.domain;

import com.ly.fn.admin.common.domain.BaseDomain;


public class UserHasRole extends BaseDomain {
    public User user;
    public Role role;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
