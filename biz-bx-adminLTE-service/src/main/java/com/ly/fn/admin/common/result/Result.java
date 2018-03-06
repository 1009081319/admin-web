package com.ly.fn.admin.common.result;

import java.util.Map;

/**
 * 类名称：Result
 * 类描述：
 * 创建人：张波波【zbb08364】
 * 创建时间：2018/3/6.13:41
 * 修改备注：
 *
 * @version 1.0.0
 */
public class Result {

    private String code;
    private String message;
    private Map<String,Object> objectMap;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getObjectMap() {
        return objectMap;
    }

    public void setObjectMap(Map<String, Object> objectMap) {
        this.objectMap = objectMap;
    }
}
