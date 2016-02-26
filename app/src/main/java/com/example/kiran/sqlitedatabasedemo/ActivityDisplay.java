package com.example.kiran.sqlitedatabasedemo;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Kiran on 15-12-2015.
 */
public class ActivityDisplay extends Activity
{
    Button bv_ShowData;
    TextView tvShowData;
    DatabaseHelperClassPrelode databaseHelperClassPrelode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showdata);
        bv_ShowData=(Button)findViewById(R.id.Bv_showdata);
        tvShowData=(TextView)findViewById(R.id.Tv_showdata);
        bv_ShowData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatat();
            }
        });

    }
    public void showDatat()
    {
        databaseHelperClassPrelode=new DatabaseHelperClassPrelode(this);
        databaseHelperClassPrelode.CreateDataBase();
//        databaseHelperClassPrelode.openDataBase();
        SQLiteDatabase sqLiteDatabase=databaseHelperClassPrelode.getReadableDatabase();
        String[] columnname={"one"};
        Cursor cursor=sqLiteDatabase.query("demo_one",columnname,null,null,null,null,null);
        if(cursor==null)
        {
            Toast.makeText(getApplicationContext(),"No rows found",Toast.LENGTH_SHORT).show();
        }
        else
        {
            StringBuilder stringBuilder=new StringBuilder("result::");
            while (cursor.moveToNext())
            {
                stringBuilder.append(cursor.getString(cursor.getColumnIndex("one")));
            }
            tvShowData.setText(stringBuilder);
        }
    }


}
