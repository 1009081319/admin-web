package com.ly.fn.admin.common.util;

import org.slf4j.Logger;

/**
 * @package：com.ly.fn.admin.common.util
 * @des：天网日志服务封装
 * @autor ：王兵【wb38113】
 * @createTime： 2017/11/29-14:58
 */
public class LoggerUtils {

    private static final String LOGGER_STRING_TEMPLATE = "<BIZ-BX-ADMINLTE-WEB><%s><%s><%s>";

    public static void info(Logger logger, String category, String subCategory, String message) {
        logger.info(String.format(LOGGER_STRING_TEMPLATE, category, subCategory, message));
    }

    public static void warn(Logger logger, String category, String subCategory, String message) {
        logger.warn(String.format(LOGGER_STRING_TEMPLATE, category, subCategory, message));
    }

    public static void error(Logger logger, String category, String subCategory, String message, Throwable throwable) {
        logger.error(String.format(category, subCategory, message), throwable);
    }

}
