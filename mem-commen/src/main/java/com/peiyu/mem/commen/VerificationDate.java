package com.peiyu.mem.commen;

import com.migr.common.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/14.
 */
public class VerificationDate {
    /**
     * 验证是否是一个有效的促销活动
     * @return
     */
    public static boolean isValidPromotion(Date startDate, Date endDate, String startTime, String endTime, String weekflag) throws ParseException {
        Date nowDate = new Date();
        if (startDate.after(nowDate) || endDate.before(nowDate)) {
            return false;
        }
        if (!isValidWeek(weekflag)) {
            return false;
        }
        if (!isValidMoment(startTime, endTime)) {
            return false;
        }
        return true;
    }

    /**
     * 验证今日是否是有效期星期
     *
     * @param weekFlag
     * @return
     */
    public static boolean isValidWeek(String weekFlag) {
        Calendar now = Calendar.getInstance();
        boolean isFirstSunday = (now.getFirstDayOfWeek() == Calendar.SUNDAY);
        int weekDay = now.get(Calendar.DAY_OF_WEEK);
        //若一周第一天为星期天，则-1
        if (isFirstSunday) {
            weekDay = weekDay - 1;
            if (weekDay == 0) {
                weekDay = 7;
            }
        }
        if (weekFlag.charAt(weekDay - 1) == '1') {
            return true;
        }
        return false;
    }

    /**
     * 验证是否满足时刻
     *
     * @param startTime
     * @param endTime
     * @return
     * @throws ParseException
     */
    public static boolean isValidMoment(String startTime, String endTime) throws ParseException {
        if (StringUtils.isBlank(startTime) && StringUtils.isBlank(endTime)) {
            return true;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date nowDate = new Date();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        if (StringUtils.isNotBlank(startTime) && StringUtils.isBlank(endTime)) {
            startTime = format.format(nowDate) + " " + startTime;
            Date startTimeDate = format1.parse(startTime);
            if (!startTimeDate.after(nowDate)) {
                return true;
            }
            return false;
        }
        if (StringUtils.isBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            endTime = format.format(nowDate) + " " + endTime;
            Date endTimeDate = format1.parse(endTime);
            if (!endTimeDate.before(nowDate)) {
                return true;
            }
            return false;
        }
        startTime = format.format(nowDate) + " " + startTime;
        endTime = format.format(nowDate) + " " + endTime;
        Date startTimeDate = format1.parse(startTime);
        Date endTimeDate = format1.parse(endTime);
        if (startTimeDate.before(nowDate) && endTimeDate.after(nowDate)) {
            return true;
        }
        return false;
    }
}
