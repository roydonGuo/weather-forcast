package com.roydon.weatherforcast4.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.roydon.weatherforcast4.bean.CityBean;

import java.util.ArrayList;
import java.util.List;

public class DBUtils {

    private Context context;
    private CityDbHelper helper;

    /**
     * 单例模式
     * @param context
     */
    public DBUtils(Context context) {
        this.context = context;
        helper = new CityDbHelper(context) {
        };
    }

    /**
     * 向表中插入
     *
     * @param
     * @return
     */
    public boolean insertData(String cityName,String tem,String updateTime) {
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put("cityName", cityName);
        values.put("tem", tem);
        values.put("updateTime", updateTime);
        long insert = db.insert(CityDatabaseConfig.TABLE_NAME, null, values);
        if (insert > 0) {
            return true;
        }
        return false;
    }

    /**
     * 根据城市名称更新数据
     * @param cityName
     * @param tem
     * @param updateTime
     * @return
     */
    public boolean updateByName(String cityName,String tem,String updateTime){
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put("cityName", cityName);
        values.put("tem", tem);
        values.put("updateTime", updateTime);
        long update = db.update(CityDatabaseConfig.TABLE_NAME,values,"cityName=?", new String[]{cityName});

        if (update>0){
            return true;
        }return false;
    }

    /**
     * 查询全部的信息
     *
     * @return
     */
    public List<CityBean> getAllCity() {
        List<CityBean> list = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + CityDatabaseConfig.TABLE_NAME, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                String cityName = cursor.getString(0);
                String tem = cursor.getString(1);
                String updateTime = cursor.getString(2);

                CityBean cityBean = new CityBean(cityName,tem,updateTime);
                list.add(cityBean);
            }
        }
        return list;
    }

    /**
     * 根据城市名查询一条信息
     */
    public CityBean getCityByName(String cityName) {
        CityBean cityBean = new CityBean();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + CityDatabaseConfig.TABLE_NAME + " where cityName=?", new String[]{cityName});
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToFirst()) {

                String cityNameResult = cursor.getString(0);
                cityBean.setName(cityName);
                break;
            }

        }
        return cityBean;

    }

    /**
     * 根据城市名删除一条数据
     * @param cityName
     * @return
     */
    public boolean delCityByName(String cityName){
        SQLiteDatabase db = helper.getReadableDatabase();

        long delRow = db.delete(CityDatabaseConfig.TABLE_NAME, "cityName=?", new String[]{cityName});
        if (delRow > 0) {
            return true;
        }
        return false;

    }

}
