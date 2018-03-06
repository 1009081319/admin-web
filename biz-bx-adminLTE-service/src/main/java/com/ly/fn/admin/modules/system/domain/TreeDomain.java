package com.ly.fn.admin.modules.system.domain;


import java.util.List;

public class TreeDomain {

    private Long id;
    private Long pid;
    private String name;
    private boolean open;
    private boolean checked;

    private List<TreeDomain> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TreeDomain> getChildren() {
        return children;
    }

    public void setChildren(List<TreeDomain> children) {
        this.children = children;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
