package com.peiyu.mem.utils;

import org.apache.commons.lang.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/1.
 */
public class DateUtil extends DateUtils {
    public static final String DEFAULT_DATE_FORMATE = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final  int TIME_DAY_MILLISECOND = 24 * 60 * 60 * 1000;
    public static final  int TIME_DAY_MILLIHOUR = 60 * 60 * 1000;
    public static final  int TIME_DAY_MILLIMINUS = 60 * 1000;
    /***
     * 根据日期 置为0点 开始生效时间 2012-09-09 00:00:00
     * @param date
     * @return
     */
    public static Date dateResetZero(Date date){
        Date rDate = setHours(date,0);
        rDate = setMinutes(rDate,0);
        rDate = setSeconds(rDate,0);
        return rDate;
    }/***
     * 根据日期 置为23点 开始生效时间 2012-09-09 23:59:59
     * @param date
     * @return
     */
    public static Date dateReset23(Date date){
        Date rDate = setHours(date,23);
        rDate = setMinutes(rDate,59);
        rDate = setSeconds(rDate,59);
        return rDate;
    }
    /**
     * 根据格式得到格式化后的日期
     *
     * @param currDate 要格式化的日期
     * @param format   日期格式，如yyyy-MM-dd
     * @return Date 返回格式化后的日期，格式由参数<code>format</code>
     *         定义，如yyyy-MM-dd，如2006-02-15
     * @see SimpleDateFormat#parse(String)
     */
    public static Date getFormatDate(String currDate, String format) {
        if (currDate == null) {
            return null;
        }
        SimpleDateFormat dtFormatdB;
        try {
            dtFormatdB = new SimpleDateFormat(format);
            return dtFormatdB.parse(currDate);
        } catch (Exception e) {
            dtFormatdB = new SimpleDateFormat(DATE_FORMAT);
            try {
                return dtFormatdB.parse(currDate);
            } catch (Exception ex) {
            }
        }
        return null;
    }
    /**
     * 得到格式化后的时间，格式为yyyy-MM-dd HH:mm:ss，如2006-02-15 15:23:45
     *
     * @param currDate 要格式化的时间
     * @return String 返回格式化后的时间，默认格式为yyyy-MM-dd HH:mm:ss，如2006-02-15 15:23:45
     * @see #getFormatDateTime(Date, String)
     */
    public static String getFormatDateTime(Date currDate) {
        return getFormatDateTime(currDate, DEFAULT_DATE_FORMATE);
    }
    /**
     * 根据格式得到格式化后的时间
     *
     * @param currDate 要格式化的时间
     * @param format   时间格式，如yyyy-MM-dd HH:mm:ss
     * @return String 返回格式化后的时间，格式由参数<code>format</code>定义，如yyyy-MM-dd
     *         HH:mm:ss
     * @see SimpleDateFormat#format(Date)
     */
    public static String getFormatDateTime(Date currDate, String format) {
        if (currDate == null) {
            return "";
        }
        SimpleDateFormat dtFormatdB = null;
        try {
            dtFormatdB = new SimpleDateFormat(format);
            return dtFormatdB.format(currDate);
        } catch (Exception e) {
            dtFormatdB = new SimpleDateFormat(DEFAULT_DATE_FORMATE);
            try {
                return dtFormatdB.format(currDate);
            } catch (Exception ex) {
            }
        }
        return "";
    }
}
