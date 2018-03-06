package com.ly.fn.admin.modules.system.domain;

import com.ly.fn.admin.common.domain.BaseDomain;


public class Role extends BaseDomain {
    private String cname;
    private String ename;

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }
}
