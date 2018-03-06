package com.ly.fn.admin.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ly.fn.admin.common.util.JsonDateSerializer;

import java.io.Serializable;
import java.util.Date;

/**
 * @package：com.ly.fn.admin.common.domain
 * @des：
 * @autor ：王兵【wb38113】
 * @createTime： 2017/11/16-17:51
 */
public abstract class BaseDomain implements Serializable{
    /**主键*/
    protected Long id;
    /**创建日期*/
    @JsonSerialize(using = JsonDateSerializer.class)
    protected Date createTime;
    /**更新日期*/
    @JsonSerialize(using = JsonDateSerializer.class)
    protected Date updateTime;
    /**删除标记*/
    protected Integer deleteFlag;
    /**备注*/
    protected String remarks;
    /**
     * dataTables search cond
     */
    @JsonIgnore
    protected int draw;
    @JsonIgnore
    protected int start = 0;
    @JsonIgnore
    protected int length = 10;
    /**快速查询关键字*/
    @JsonIgnore
    protected String fastSearchWord;
    /**排序描述*/
    @JsonIgnore
    protected String sortStr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length < 15 ? 15 : length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getDraw() {
        return draw;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public String getFastSearchWord() {
        return fastSearchWord;
    }

    public void setFastSearchWord(String fastSearchWord) {
        this.fastSearchWord = fastSearchWord;
    }

    public String getSortStr() {
        return sortStr;
    }

    public void setSortStr(String sortStr) {
        this.sortStr = sortStr;
    }
}
