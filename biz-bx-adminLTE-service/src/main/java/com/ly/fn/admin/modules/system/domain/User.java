package com.ly.fn.admin.modules.system.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ly.fn.admin.common.domain.BaseDomain;
import com.ly.fn.admin.common.util.JsonDateSerializer;

import java.util.Date;

/**
 * @package：com.ly.fn.admin.modules.system.domain
 * @des：
 * @autor ：王兵【wb38113】
 * @createTime： 2017/12/29-15:40
 */
public class User extends BaseDomain{
    /**openId*/
    private String openId;
    /**登录名*/
    private String loginName;
    /**手机号*/
    private String phone;
    /**邮箱*/
    private String email;
    /**密码*/
    private String password;
    /**昵称*/
    private String nickName;
    /**上次登录时间*/
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date lastLoginTime;
    /**
     * 所属角色
     */
    private Role role;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
