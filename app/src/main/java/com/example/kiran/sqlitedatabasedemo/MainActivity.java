package com.example.kiran.sqlitedatabasedemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DabaseHandlerClass dbh;
    Cursor cursoroject;
    public String[] yuga_one_columns={"id","name"};
    TextView tv_columns_Fill;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText ed_id=(EditText) findViewById(R.id.editText1);
        final EditText ed_name=(EditText) findViewById(R.id.editText2);
        Button bt_Submit=(Button)findViewById(R.id.senddata);
        dbh = new DabaseHandlerClass(this);
        bt_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddDataTo_yuga_one(Integer.parseInt(ed_id.getText().toString()),ed_name.getText().toString());
                  cursoroject=SHOWDATA();
                SHOWDATA(cursoroject);
            }
        });
    }
    public void AddDataTo_yuga_one(int id,String name)
    {
        SQLiteDatabase sqLiteDatabase=dbh.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("id",id);
        contentValues.put("Name",name);
     long row_insertcheck= sqLiteDatabase.insert("yuga_one", null, contentValues);
        if(row_insertcheck!=-1)
        {
            Toast.makeText(getApplicationContext(),"rowinsertd",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"row not insertd",Toast.LENGTH_SHORT).show();
        }
    }
    public Cursor SHOWDATA()
    {
        SQLiteDatabase yuga_one_sqLiteDatabase=dbh.getReadableDatabase();
        Cursor cursor_return=yuga_one_sqLiteDatabase.query("yuga_one",yuga_one_columns,null,null,null,null,null);
        Log.d("Cursor", "" + cursor_return);
//        startManagingCursor(cursor_return);
        return  cursor_return;
    }
    public void SHOWDATA(Cursor cursor)
    {
        StringBuilder stirngbulidercolumns=new StringBuilder("\n Result is::");
        while(cursor.moveToNext())
        {
            stirngbulidercolumns.append(cursor.getInt(cursor.getColumnIndex("id"))).append(":::").append(cursor.getString(cursor.getColumnIndex("name")));
        }
        tv_columns_Fill=(TextView)findViewById(R.id.textView1);
        tv_columns_Fill.setText(stirngbulidercolumns);
    }
}
