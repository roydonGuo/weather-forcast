package com.roydon.weatherforcast4.utils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SystemTimeUtils {

    public static String getSystemTime(){
        long time=System.currentTimeMillis();//long now = android.os.SystemClock.uptimeMillis();

        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date d1=new Date(time);

        String t1=format.format(d1);

        Log.e("time", t1);
        return t1;
    }

}
