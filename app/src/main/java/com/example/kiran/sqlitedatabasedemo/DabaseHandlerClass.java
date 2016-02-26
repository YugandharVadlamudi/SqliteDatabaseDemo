package com.example.kiran.sqlitedatabasedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Kiran on 14-12-2015.
 */
public class DabaseHandlerClass extends SQLiteOpenHelper {

    static String name="yuga_one";
    static int version=1;

    public DabaseHandlerClass(Context context) {

        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("CREATE TABLE "+name+"( id INTEGER, name TEXT);");
        db.execSQL("CREATE TABLE "+name+"( id INTEGER, name TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+name+";");
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

}
