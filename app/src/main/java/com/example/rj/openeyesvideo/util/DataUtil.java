package com.example.rj.openeyesvideo.util;

import com.google.android.exoplayer.C;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by rj on 2018/1/12.
 */

public class DataUtil {

    public static String timeFormat(long date){
        Date time=new Date(date);
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(time);
        String year=calendar.get(Calendar.YEAR)+"/";
        String month;
        String day;
        if(calendar.get(Calendar.MONTH)<10){
            month="0"+calendar.get(Calendar.MONTH)+"/";
        }else {
            month=calendar.get(Calendar.MONTH)+"/";
        }
        if (calendar.get(Calendar.DAY_OF_MONTH)<10){
            day="0"+calendar.get(Calendar.DAY_OF_MONTH);
        }else {
            day=calendar.get(Calendar.DAY_OF_MONTH)+"";
        }
        return year+month+day;
    }

}
