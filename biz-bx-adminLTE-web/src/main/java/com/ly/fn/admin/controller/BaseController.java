package com.ly.fn.admin.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;


public class BaseController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取DataTables排序字段
     */
    public String getSortStr(HttpServletRequest request) {
        String orderColumn = request.getParameter("order[0][column]");
        String sort = request.getParameter("order[0][dir]");
        String sortStr = "";
        if (!StringUtils.isEmpty(orderColumn)) {
            String sortName = request.getParameter("columns[" + orderColumn + "][name]");
            sortStr = sortName + " " + sort;
        }
        return sortStr;
    }
}
