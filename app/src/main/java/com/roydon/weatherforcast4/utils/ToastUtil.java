package com.roydon.weatherforcast4.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {

    /**
     * 长消息
     * @param context 上下文参数
     * @param cs 内容
     */
    public static void showLongToast(Context context,CharSequence cs){
        Toast.makeText(context.getApplicationContext(), cs, Toast.LENGTH_LONG).show();
    }

    /**
     * 短消息
     * @param context
     * @param cs
     */
    public static void showShotToast(Context context,CharSequence cs){
        Toast.makeText(context.getApplicationContext(), cs, Toast.LENGTH_LONG).show();
    }


}
