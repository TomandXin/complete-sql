package com.tom.plugin;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PluginDateUtils {

    private static String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";

    private static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT);

    /**
     * 将时间转换为字符串类型
     *
     * @param date
     * @return
     */
    public static String formatToString(Date date) {
        if (null == date) {
            return null;
        }
        return SIMPLE_DATE_FORMAT.format(date);
    }
}
