package com.ly.fn.admin.modules.system;

/**
 * @package：com.ly.fn.admin.modules.system
 * @des：
 * @autor ：王兵【wb38113】
 * @createTime： 2018/1/12-11:15
 */
public enum TreeTypeEnum {
    PERMISSION("permission", "权限树"), ORGANIZATION("organization", "组织机构树");
    public final String type;
    public final String name;

    TreeTypeEnum(String type, String name) {
        this.type = type;
        this.name = name;
    }
}
