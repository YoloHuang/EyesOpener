package com.example.hzj.EyeOpener.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by hzj on 2018/1/12.
 * 将Long类型的Date装换为日期
 */

public class DataUtil {

    public static String timeFormat(long date) {
        Date time = new Date(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        String year = calendar.get(Calendar.YEAR) + "/";
        String month;
        String day;
        int monthnum = calendar.get(Calendar.MONTH) + 1;
        if (monthnum < 10) {
            month = "0" + monthnum + "/";
        } else {
            month = monthnum + "/";
        }
        if (calendar.get(Calendar.DAY_OF_MONTH) < 10) {
            day = "0" + calendar.get(Calendar.DAY_OF_MONTH);
        } else {
            day = calendar.get(Calendar.DAY_OF_MONTH) + "";
        }
        return year + month + day;
    }

}
