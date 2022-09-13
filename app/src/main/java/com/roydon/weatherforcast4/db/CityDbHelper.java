package com.roydon.weatherforcast4.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class CityDbHelper extends SQLiteOpenHelper {

    public CityDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public CityDbHelper(Context context) {
        super(context, CityDatabaseConfig.DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists "+CityDatabaseConfig.TABLE_NAME+"(cityName text,tem text,updateTime text)";
        db.execSQL(sql);
        Log.i(CityDatabaseConfig.DATABASE_NAME,"created success!!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.i(CityDatabaseConfig.DATABASE_NAME,"updated success!!");
    }

}
