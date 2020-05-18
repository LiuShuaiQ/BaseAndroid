package com.lem.common_tool;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期操作工具类.
 */

public class DateUtil {

    /**
     * 英文简写如：2016
     */
    public static String FORMAT_Y = "yyyy";

    /**
     * 英文简写如：12:01
     */
    public static String FORMAT_HM = "HH:mm";

    /**
     * 英文简写如：12:01:00
     */
    public static String FORMAT_HMS = "HH:mm:ss";

    /**
     * 英文简写如：1-12
     */
    public static String FORMAT_MD = "MM-dd";

    /**
     * 英文简写如：1-12 12:01
     */
    public static String FORMAT_MDHM = "MM-dd HH:mm";

    /**
     * 英文简写（默认）如：2016-12-01
     */
    public static String FORMAT_YMD = "yyyy-MM-dd";

    /**
     * 英文全称  如：2016-12-01 23:15
     */
    public static String FORMAT_YMDHM = "yyyy-MM-dd HH:mm";

    /**
     * 英文全称  如：2016-12-01 23:15:06
     */
    public static String FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 精确到毫秒的完整时间    如：yyyy-MM-dd HH:mm:ss.S
     */
    public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";

    /**
     * 精确到毫秒的完整时间    如：yyyy-MM-dd HH:mm:ss.S
     */
    public static String FORMAT_FULL_SN = "yyyyMMddHHmmssS";

    /**
     * 中文简写  如：2016年12月01日
     */
    public static String FORMAT_YMD_CN = "yyyy年MM月dd日";

    /**
     * 中文简写  如：2016年12月01日  12时
     */
    public static String FORMAT_YMDH_CN = "yyyy年MM月dd日 HH时";

    /**
     * 中文简写  如：2016年12月01日  12时12分
     */
    public static String FORMAT_YMDHM_CN = "yyyy年MM月dd日 HH时mm分";

    /**
     * 中文全称  如：2016年12月01日  23时15分06秒
     */
    public static String FORMAT_YMDHMS_CN = "yyyy年MM月dd日  HH时mm分ss秒";

    /**
     * 精确到毫秒的完整中文时间
     */
    public static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";

    public static Calendar calendar = null;
    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String FORMAT2 = "MM-dd";
    private static final String FORMAT3 = "yyyy-MM-dd";

    public static Date str2Date(String str) {
        return str2Date(str, null);
    }

    public static Date str2Date(String str, String format) {
        if (str == null || str.length() == 0) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT;
        }
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Calendar str2Calendar(String str) {
        return str2Calendar(str, null);
    }

    public static Calendar str2Calendar(String str, String format) {

        Date date = str2Date(str, format);
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        return c;
    }

    public static String date2Str(Calendar c) {// yyyy-MM-dd HH:mm:ss
        return date2Str(c, null);
    }

    public static String date2Str(Calendar c, String format) {
        if (c == null) {
            return null;
        }
        return date2Str(c.getTime(), format);
    }

    public static String date2Str(Date d) {// yyyy-MM-dd HH:mm:ss
        return date2Str(d, null);
    }

    public static String date2Str(Date d, String format) {// yyyy-MM-dd HH:mm:ss
        if (d == null) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String s = sdf.format(d);
        return s;
    }

    public static String getCurDateStr() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" +
                c.get(Calendar.DAY_OF_MONTH) + "-" +
                c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) +
                ":" + c.get(Calendar.SECOND);
    }

    /**
     * 获得当前日期的字符串格式
     *
     * @param format 格式化的类型
     * @return 返回格式化之后的事件
     */
    public static String getCurDateStr(String format) {
        Calendar c = Calendar.getInstance();
        return date2Str(c, format);
    }

    /**
     * @param time 当前的时间
     * @return 格式到秒
     */

    public static String getMillon(long time) {

        return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(time);
    }

    /**
     * @param time 当前的时间
     * @return 当前的天
     */
    public static String getDay(long time) {

        return new SimpleDateFormat("yyyy-MM-dd").format(time);
    }

    /**
     * @param time 时间
     * @return 返回一个毫秒
     */
    // 格式到毫秒
    public static String getSMillon(long time) {

        return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS").format(time);
    }

    /**
     * 在日期上增加数个整月
     *
     * @param date 日期
     * @param n    要增加的月数
     * @return 增加数个整月
     */
    public static Date addMonth(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, n);
        return cal.getTime();
    }

    /**
     * 在日期上增加天数
     *
     * @param date 日期
     * @param n    要增加的天数
     * @return 增加之后的天数
     */
    public static Date addDay(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, n);
        return cal.getTime();
    }

    /**
     * 获取距现在某一小时的时刻
     *
     * @param format 格式化时间的格式
     * @param h      距现在的小时 例如：h=-1为上一个小时，h=1为下一个小时
     * @return 获取距现在某一小时的时刻
     */
    public static String getNextHour(String format, int h) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = new Date();
        date.setTime(date.getTime() + h * 60 * 60 * 1000);
        return sdf.format(date);
    }

    /**
     * 获取时间戳
     *
     * @return 获取时间戳
     */
    public static String getTimeString() {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_FULL);
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }

    /**
     * 功能描述：返回月
     *
     * @param date Date 日期
     * @return 返回月份
     */
    public static int getYear(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 功能描述：返回月
     *
     * @param date Date 日期
     * @return 返回月份
     */
    public static int getMonth(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 功能描述：返回日
     *
     * @param date Date 日期
     * @return 返回日份
     */
    public static int getDay(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 功能描述：返回小
     *
     * @param date 日期
     * @return 返回小时
     */
    public static int getHour(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 功能描述：返回分
     *
     * @param date 日期
     * @return 返回分钟
     */
    public static int getMinute(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 获得默认的 date pattern
     *
     * @return 默认的格式
     */
    public static String getDatePattern() {

        return FORMAT_YMDHMS;
    }

    /**
     * 返回秒钟
     *
     * @param date Date 日期
     * @return 返回秒钟
     */
    public static int getSecond(Date date) {
        calendar = Calendar.getInstance();

        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 使用预设格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @return 提取字符串的日期
     */
    public static Date parse(String strDate) {
        return parse(strDate, getDatePattern());
    }

    /**
     * 功能描述：返回毫
     *
     * @param date 日期
     * @return 返回毫
     */
    public static long getMillis(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }

    /**
     * 按默认格式的字符串距离今天的天数
     *
     * @param date 日期字符串
     * @return 按默认格式的字符串距离今天的天数
     */
    public static int countDays(String date) {
        long t = Calendar.getInstance().getTime().getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(parse(date));
        long t1 = c.getTime().getTime();
        return (int) (t / 1000 - t1 / 1000) / 3600 / 24;
    }

    /**
     * 使用用户格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @param pattern 日期格式
     * @return 提取字符串日期
     */
    public static Date parse(String strDate, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 按用户格式字符串距离今天的天数
     *
     * @param date   日期字符串
     * @param format 日期格式
     * @return 按用户格式字符串距离今天的天数
     */
    public static int countDays(String date, String format) {
        long t = Calendar.getInstance().getTime().getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(parse(date, format));
        long t1 = c.getTime().getTime();
        return (int) (t / 1000 - t1 / 1000) / 3600 / 24;
    }

    /**
     * 获取当前时间
     */
    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(System.currentTimeMillis());
    }

    /**
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT);
        long lt = new Long(s);
        Date date = new Date(lt * 1000);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s, String format) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        long lt = new Long(s);
        Date date = new Date(lt * 1000);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * 将时间转换为时间戳
     */
    public static long dateToStamp(String s) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT);
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        return ts;
    }

    /**
     * 将时间转换为时间戳
     *
     * @throws ParseException
     */
    public static long dateToStamp(String s, String format) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        return ts;
    }

    /**
     * 获取新闻时间格式
     */
    public static String getNewsTime(String source) {
        try {
            Calendar calendar = Calendar.getInstance();//日历对象
            Calendar calendar2 = Calendar.getInstance();//日历对象
            SimpleDateFormat sf3 = new SimpleDateFormat(FORMAT);
            Date newsDate = sf3.parse(source);
            calendar.setTime(newsDate);
            Date nowDate = new Date(System.currentTimeMillis());
            calendar2.setTime(nowDate);
            long betweenTime = nowDate.getTime() - newsDate.getTime();
            long minute = betweenTime / 1000 / 60;
            if (minute < 1) {
                return "刚刚";
            } else if (minute < 60) {
                return (minute) + "分钟前";
            } else if (minute < 1440) {
                return (minute / 60) + "小时前";
            } else if (minute < 7200) {
                return (minute / 60 / 24) + "天前";
            } else if (calendar.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)) {
                SimpleDateFormat sdf = new SimpleDateFormat(FORMAT2);
                String format = sdf.format(newsDate);
                return format;
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat(FORMAT3);
                String format = sdf.format(newsDate);
                return format;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * 获取新闻时间格式
     */
    public static String getAppsTime(String source) {
        try {
            if (TextUtils.isEmpty(source)) {
                return "最近未使用";
            }
            Calendar calendar = Calendar.getInstance();//日历对象
            Calendar calendar2 = Calendar.getInstance();//日历对象
            SimpleDateFormat sf3 = new SimpleDateFormat(FORMAT3);
            Date newsDate = sf3.parse(source);
            calendar.setTime(newsDate);
            Date nowDate = new Date(System.currentTimeMillis());
            calendar2.setTime(nowDate);
            long betweenTime = nowDate.getTime() - newsDate.getTime();
            long minute = betweenTime / 1000 / 60;
            if (minute < 1) {
                return "刚刚使用";
            } else if (minute < 60) {
                return "刚刚使用";
            } else if (minute < 1440) {
                return "刚刚使用";
            } else if (minute < 43200) {
                return (minute / 60 / 24) + "天未使用";
            } else if (minute < 43200 * 6) {
                return (minute / 60 / 24 / 30) + "个月未使用";
            } else {
                return "最近未使用";
            }
            //else if (calendar.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)) {
            //  SimpleDateFormat sdf = new SimpleDateFormat(FORMAT2);
            //  String format = sdf.format(newsDate);
            //  return format;
            //} else {
            //  SimpleDateFormat sdf = new SimpleDateFormat(FORMAT3);
            //  String format = sdf.format(newsDate);
            //  return format;
            //}
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * 获取新闻时间格式
     */
    public static String getNewsTime(String source, String format) {
        try {
            SimpleDateFormat sf3 = new SimpleDateFormat(format);
            Date newsDate = sf3.parse(source);
            Date nowDate = new Date(System.currentTimeMillis());
            long betweenTime = nowDate.getTime() - newsDate.getTime();
            int minute = (int) betweenTime / 1000 / 60;
            if (minute < 1) {
                return "刚刚";
            } else if (minute < 60) {
                return "1小时前";
            } else if (minute < 1440) {
                return (minute / 60) + "小时前";
            } else {
                return source;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * 获取两个时间差
     */
    public static String getBetween(long startTime) {
        try {
            long now = new Date().getTime();

            long t = startTime - now;

            int d = (int) (t / 1000 / 60 / 60 / 24);
            int h = (int) (t / 1000 / 60 / 60 - d * 24);
            int m = (int) (t / 1000 / 60 - h * 60 - d * 24 * 60) + 1;

            return d + "天" + h + "小时" + m + "分钟";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "00天00小时00分钟";
    }

    public static String getBetween3(long startTime) {
        try {
            long now = new Date().getTime();

            long t = startTime - now;

            int d = (int) (t / 1000 / 60 / 60 / 24);
            int h = (int) (t / 1000 / 60 / 60);
            int m = (int) (t / 1000 / 60 - h * 60);
            int s = (int) (t / 1000 - m * 60 - h * 60 * 60) + 1;

            if (t >= 24 * 60 * 60 * 1000) {
                return "距离开播，还有" + d + "天";
            } else if (t > 0) {
                return ("开播倒计时\n" + (h < 10 ? ("0" + h) : (h + "")) + ":" +
                        (m < 10 ? ("0" + m) : (m + "")) + ":" +
                        (s < 10 ? ("0" + s) : (s + "")));
            } else {
                //                return "请点击右上角图钉标志\n推送新的开播时间";
                return "已逾期";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * 获取
     */
    public static String getBriefTimeStr(long time) {
        Date date = new Date(time * 1000);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Date nowDate = new Date();
        Calendar nowCal = Calendar.getInstance();
        nowCal.setTime(nowDate);
        if (cal.get(Calendar.DAY_OF_YEAR) == nowCal.get(Calendar.DAY_OF_YEAR)) {
            return DateUtil.stampToDate(time + "", FORMAT_HM);
        } else if (cal.get(Calendar.YEAR) == nowCal.get(Calendar.YEAR)) {
            return DateUtil.stampToDate(time + "", FORMAT_MD);
        } else {
            return DateUtil.stampToDate(time + "", FORMAT_YMD);
        }
    }

    /**
     * 获取两个时间差:时:分:秒
     */
    public static int getBetweenDay(long startTime) {
        try {
            long now = new Date().getTime();
            long t = now - startTime;

            int d = (int) (t / 1000 / 60 / 60 / 24);
            //            int h = (int) (t / 1000 / 60 / 60);
            //            int m = (int) (t / 1000 / 60 - h * 60);
            //            int s = (int) (t / 1000 - m * 60 - h * 60 * 60) + 1;

            if (t >= 24 * 60 * 60 * 1000) {
                return d;
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    //判断选择的日期是否是今天
    public static boolean isToday(long time) {
        return isThisTime(time, "yyyy-MM-dd");
    }

    private static boolean isThisTime(long time, String pattern) {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String param = sdf.format(date);//参数时间
        String now = sdf.format(new Date());//当前时间
        return param.equals(now);
    }

    /**
     * 将时间戳转换成新闻时间格式
     */
    public static String getNewsTime(long source) {
        try {
            Date newsDate = new Date(source);
            Date nowDate = new Date(System.currentTimeMillis());
            long betweenTime = nowDate.getTime() - newsDate.getTime();
            long minute = betweenTime / 1000 / 60;
            if (minute < 1) {
                return "刚刚";
            } else if (minute < 60) {
                return minute + "分钟前";
            } else if (minute < 1440) {
                return (minute / 60) + "小时前";
            } else {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * 剪短格式时间
     */
    public static String date2ShortStr(Date d) {// yyyy-MM-dd HH:mm:ss
        if (d == null) {
            return null;
        }
        SimpleDateFormat sdf = null;
        if (isToday(d.getTime())) {
            sdf = new SimpleDateFormat(FORMAT_HM);
        } else {
            sdf = new SimpleDateFormat(FORMAT_MD);
        }

        String s = sdf.format(d);
        return s;
    }
}
