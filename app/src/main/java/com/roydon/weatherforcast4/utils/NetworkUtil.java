package com.roydon.weatherforcast4.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtil {
    //https://tianqiapi.com/api?version=v1&appid=36646344&appsecret=c1lgQbP9
    //https://v0.yiketianqi.com/api?unescape=1&version=v91&appid=43656176&appsecret=I42og6Lm
    // 一天只有五十次机会
    /**
     * 18625561：27XjzrB7
     * 67342285：5XgTk31r
     * 19267789：Dhu3DShY
     * 此appid和appsecret请自行前往https://tianqiapi.com/注册账号申请
     */
    public static final String URL_WEATHER = "http://tianqiapi.com/api?unescape=1&version=v9&appid=18625561&appsecret=27XjzrB7";
    // https://tianqiapi.com/api?version=v1&appid=67342285&appsecret=5XgTk31r //此行链接为老版本已失效
    // https://v0.yiketianqi.com/api?unescape=1&version=v9&appid=67342285&appsecret=5XgTk31r //此行链接为老版本已失效
    // https://v1.yiketianqi.com/api?unescape=1&version=v91&appid=18625561&appsecret=27XjzrB7 //此行链接响应体不完全

    public static String getWeatherByCity(String cityName) {
        String urlGetJson = URL_WEATHER+"&city="+cityName;
        String result = "";
        HttpURLConnection connection = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        // 连接网络
        try {
            URL urL = new URL(urlGetJson);
            connection = (HttpURLConnection) urL.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5*5000);
            connection.setReadTimeout(5*5000);

            // 从连接中读取数据(二进制)
            InputStream inputStream = connection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            // 二进制流送入缓冲区
            bufferedReader = new BufferedReader(inputStreamReader);

            // 从缓存区中一行行读取字符串
            StringBuilder stringBuilder = new StringBuilder();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            result = stringBuilder.toString();


        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                connection.disconnect();
            }

            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return result;
    }

}
