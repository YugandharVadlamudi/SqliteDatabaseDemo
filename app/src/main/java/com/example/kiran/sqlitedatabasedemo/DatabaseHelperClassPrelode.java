package com.example.kiran.sqlitedatabasedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

/**
 * Created by Kiran on 15-12-2015.
 */
public class DatabaseHelperClassPrelode extends SQLiteOpenHelper {
    private static String DB_PATH ;
    public static String DB_NAME="yuga_one_demo.db";
    SQLiteDatabase sqLiteDatabase;
    static int version=1;
    Context context;

    public DatabaseHelperClassPrelode(Context contextn) {
        super(contextn, DB_NAME,null, version);
        this.context=contextn;
        if(android.os.Build.VERSION.SDK_INT >= 4.2){
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        }
        else
        {
            DB_PATH="/data/data"+context.getPackageName()+"/databases/";
        }
    }
    public boolean checkExists()
    {
        SQLiteDatabase sqLiteDatabase=null;
        try
        {
            String my_path=DB_PATH+DB_NAME;
            sqLiteDatabase=SQLiteDatabase.openDatabase(my_path,null,SQLiteDatabase.OPEN_READONLY);
        }
        catch(SQLiteException e){
            //database does't exist yet.
        }
        if(sqLiteDatabase!=null)
        {
            sqLiteDatabase.close();
        }
        return sqLiteDatabase!=null ? true : false;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void CreateDataBase()
    {
        boolean BooleanCheckDataBase= false;

            BooleanCheckDataBase = checkExists();

        Log.d("BooleanCheck",""+BooleanCheckDataBase);
        if(BooleanCheckDataBase)
        {

        }
        else
        {
            this.getReadableDatabase();
            try {
                CopyDataBase();
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("Error in C DataBase", ".....");
            }
        }
    }
    public boolean CheckDataBase() throws SQLException {
        SQLiteDatabase sqLiteDatabase=null;
        String path_database=DB_PATH+DB_NAME;
        File outFile = new File(Environment.getDataDirectory(), path_database);
        outFile.setWritable(true);

        sqLiteDatabase=SQLiteDatabase.openDatabase(outFile.getAbsolutePath(),null,SQLiteDatabase.OPEN_READONLY);
        /*Log.d("sqlite_one",""+sqLiteDatabase);
        if(sqLiteDatabase!=null) {
            sqLiteDatabase.close();
        }

        return sqLiteDatabase!=null? true : false;*/
        return false;
    }
    public void CopyDataBase() throws IOException {
        InputStream inputStream=context.getAssets().open(DB_NAME);
        String path_database=DB_PATH+DB_NAME;
        OutputStream outputStream=new FileOutputStream(path_database);
        byte[] buffer=new byte[1024];
        int length;
        while((length=inputStream.read(buffer))>0)
        {
            outputStream.write(buffer,0,length);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();

    }
    public  void openDataBase()
    {
        String path_database=DB_PATH+DB_NAME;
        sqLiteDatabase=SQLiteDatabase.openDatabase(path_database, null, SQLiteDatabase.OPEN_READONLY);
    }
}
