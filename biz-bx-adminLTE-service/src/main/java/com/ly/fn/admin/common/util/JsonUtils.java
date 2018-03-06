package com.ly.fn.admin.common.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.ly.fn.admin.common.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * 类名称：JsonUtils
 * 类描述：
 * 创建人：张波波【zbb08364】
 * 创建时间：2018/3/6.13:55
 * 修改备注：
 *
 * @version 1.0.0
 */
public class JsonUtils {
    private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    private static Gson gson = new Gson();

    /**
     * 将map转换成jsonString
     * @param map
     */
    public static String toJson(Map<String, Object> map) {
        return gson.toJson(map);
    }

    public static String toJson(Object object) {
        String json = null;
        try {
            ObjectMapper om = new ObjectMapper();
            json = om.writeValueAsString(object);
        } catch (IOException e) {
            throw new ServiceException("将object转换json串异常");
        }
        return json;
    }

    public static Map<String, Object> toMap(String json) {
        ObjectMapper om = new ObjectMapper();
        Map<String, Object> map;
        try {
            map = om.readValue(json, Map.class);
        } catch (IOException e) {
            throw new ServiceException("json字符串转map异常");
        }
        return map;
    }

    public static <T> T fromJson(String jsonString, Class<T> tClass) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        try {
            ObjectMapper om = new ObjectMapper();
            om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return om.readValue(jsonString, tClass);
        } catch (IOException e) {
            throw new ServiceException("json字符串转对象异常", e);
        }
    }
}
