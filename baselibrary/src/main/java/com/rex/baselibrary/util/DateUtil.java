package com.rex.baselibrary.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期格式化辅助类
 * 由于SimpleDateFormat不是线程安全的，此处全部采用ThreadLocal存储
 *
 * @author Ren ZeQiang
 * @since 2018/10/29.
 */
public class DateUtil {

    private static final class SimpleDateFormatTL extends ThreadLocal<DateFormat> {
        private String formatStr;

        public SimpleDateFormatTL(String formatStr) {
            this.formatStr = formatStr;
        }

        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat(formatStr, Locale.CHINA);
        }
    }

    private static final ThreadLocal<DateFormat> month = new SimpleDateFormatTL("yyyy-MM");
    private static final ThreadLocal<DateFormat> hourMinute = new SimpleDateFormatTL("HH:mm");
    private static final ThreadLocal<DateFormat> day = new SimpleDateFormatTL("yyyy-MM-dd");
    private static final ThreadLocal<DateFormat> dayTime = new SimpleDateFormatTL("yyyy-MM-dd HH:mm:ss");
    private static final ThreadLocal<DateFormat> detailTime = new SimpleDateFormatTL("yyyy-MM-dd HH:mm:ss.SSS");

    public static DateFormat getHourMinuteFormat() {
        return hourMinute.get();
    }

    public static DateFormat getYearMonthFormat() {
        return month.get();
    }

    public static DateFormat getDayFormat() {
        return day.get();
    }

    public static DateFormat getDayTimeFormat() {
        return dayTime.get();
    }

    public static DateFormat getDetailTimeFormat() {
        return detailTime.get();
    }

    public static String formatDate(Date date) {
        return getDayFormat().format(date);
    }

    public static String formatDate(Date date, String formatString) {
        return new SimpleDateFormat(formatString, Locale.getDefault()).format(date);
    }

    public static Date parseDate(String date) {
        try {
            return getDayFormat().parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Date parseDate(String date, String formatString) {
        try {
            return new SimpleDateFormat(formatString, Locale.getDefault()).parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取2个日期天数差
     *
     * @param d1 日期1
     * @param d2 日期2
     * @return 天数差
     */
    public static int diffDays(Date d1, Date d2) {
        Calendar c = Calendar.getInstance();
        c.setTime(d1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        final long t1 = c.getTimeInMillis();

        c.setTime(d2);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        final long t2 = c.getTimeInMillis();

        final long zeroTime = 86400000L;

        return (int) (Math.abs(((t1 - t2) / zeroTime)));
    }

    /**
     * 格式化日期为人性化文字
     *
     * @param date 日期
     * @return 文字
     */
    public static String formatDateString(Date date) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c2.setTime(date);

        if (c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH)
                && c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)) {
            return "今天";
        } else if (c1.get(Calendar.DAY_OF_MONTH) - c2.get(Calendar.DAY_OF_MONTH) == 1
                && c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)) {
            return "昨天";
        } else if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)) {
            return c2.get(Calendar.DAY_OF_MONTH) + "日";
        } else if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)) {
            return (c2.get(Calendar.MONTH) + 1) + "月" + c2.get(Calendar.DAY_OF_MONTH) + "日";
        } else {
            return c2.get(Calendar.YEAR) + "年" + (c2.get(Calendar.MONTH) + 1) + "月" + c2.get(Calendar.DAY_OF_MONTH)
                    + "日";
        }
    }

    /**
     * 抹除日期小于天单位的数值为0，抹除后小时，分钟，秒钟均为0
     *
     * @param calendar Calendar
     * @return 抹除之后的日期值
     */
    public static Calendar setDayZeroTime(Calendar calendar) {
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        return calendar;
    }

}
