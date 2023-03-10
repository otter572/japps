package com.japps.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

    public static final int MILLISECONDS_OF_HOUR = 3600000;

    public static final int HOURS_OF_DAY = 24;

    public static final String FORMAT_MINUTE = "yyyyMMdd_HH:mm";

    public static final String FORMAT_SECOND = "yyyyMMdd_HH:mm:ss";

    public static String getCurrentTimeStr(String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(new Date());
    }

    public static long strToMillis(String str, String format) {
        try {
            return new SimpleDateFormat(format).parse(str).getTime();
        } catch (ParseException e) {
            return -1;
        }
    }

    /**
     * 计算过去某时与当前时间的时间间隔，精确到小时
     *
     * @param before 靠前的时间（默认格式为yyyyMMdd_HHmm）
     * @return {@link String}
     */
    public static String countDiffToPresent(String before) {
        Long beforeMillis = strToMillis(before, FORMAT_MINUTE);
        if (beforeMillis < 0) {
            return "error";
        }
        Long currentTimeMillis = System.currentTimeMillis();
        Long diffMillis = currentTimeMillis - beforeMillis;
        int diffHours = (int)(diffMillis / MILLISECONDS_OF_HOUR);
        if (diffHours < HOURS_OF_DAY) {
            return diffHours + "H";
        }
        int diffDays = diffHours / HOURS_OF_DAY;
        diffHours = diffHours % HOURS_OF_DAY;
        if (diffHours == 0) {
            return diffDays + "D";
        }
        return diffDays + "D_" + diffHours + "H";
    }

}
