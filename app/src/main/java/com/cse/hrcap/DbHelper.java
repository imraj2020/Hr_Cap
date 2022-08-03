package com.cse.hrcap;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "student.db";
    private static final String TABLE_NAME = "student_details";
    private static final String ID = "_id";
    private static final String Date = "Dates";
    private static final String Time = "Times";
    private static final int DATABASE_VERSION_NO = 1;
    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+Date+" VARCHAR(255),"+Time+" VARCHAR(255));";
    private Context context;

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION_NO);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            Toast.makeText(context,"onCreate is called",Toast.LENGTH_SHORT).show();
            db.execSQL(CREATE_TABLE);
        }catch (Exception e)
        {
            Toast.makeText(context,"Exception : "+e,Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void insertRecord(String date, String time) {
        SQLiteDatabase dbc = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Date, date);
        values.put(Time, time);

      //  Log.e("Values are ", String.valueOf(values));

        dbc.insert(TABLE_NAME, null, values);
        Toast.makeText(context, "database "+values, Toast.LENGTH_LONG).show();
        dbc.close();
    }
}