package com.ly.fn.admin.common.page;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class PageInfo<T> implements Serializable {
    @JsonProperty("draw")
    private int draw = 0;
    @JsonProperty("recordsTotal")
    private int totalCount = 0;
    @JsonProperty("recordsFiltered")
    private int recordsFiltered = 15;
    @JsonProperty("data")
    private List<T> result;

    public PageInfo() {

    }

    public PageInfo(int draw, int totalCount, List<T> list) {
        setDraw(draw);
        setTotalCount(totalCount);
        setRecordsFiltered(totalCount);
        setResult(list);
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
