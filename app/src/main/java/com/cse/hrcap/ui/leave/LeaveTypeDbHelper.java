package com.cse.hrcap.ui.leave;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class LeaveTypeDbHelper extends SQLiteOpenHelper {
    final int i=1;
    private static final String DATABASE_NAME = "leavetype.db";
    private static final String TABLE_NAME = "leave_type";
    private static final String LeaveType = "leavetype";
    private static final int DATABASE_VERSION_NO = 1;
    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+LeaveType+" TEXT);";
    public static  final  String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;
    private Context context;

    public LeaveTypeDbHelper(@Nullable Context context) {
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

    public void insertRecord(String leavetype1, String leavetype2, String leavetype3, String leavetype4, String leavetype5, String leavetype6, String leavetype7, String leavetype8, String leavetype9, String leavetype10) {
        SQLiteDatabase dbs = getWritableDatabase();


            ContentValues values1 = new ContentValues();
            values1.put(LeaveType, leavetype1);
            ContentValues values2 = new ContentValues();
            values2.put(LeaveType, leavetype2);
            ContentValues values3 = new ContentValues();
            values3.put(LeaveType, leavetype3);
            ContentValues values4 = new ContentValues();
            values4.put(LeaveType, leavetype4);
            ContentValues values5 = new ContentValues();
            values5.put(LeaveType, leavetype5);
            ContentValues values6 = new ContentValues();
            values6.put(LeaveType, leavetype6);
            ContentValues values7 = new ContentValues();
            values7.put(LeaveType, leavetype7);
            ContentValues values8 = new ContentValues();
            values8.put(LeaveType, leavetype8);
            ContentValues values9 = new ContentValues();
            values9.put(LeaveType, leavetype9);
            ContentValues values10 = new ContentValues();
            values10.put(LeaveType, leavetype10);

//        values.put(Time, time);

            //  Log.e("Values are ", String.valueOf(values));

            dbs.insert(TABLE_NAME, null, values1);
            dbs.insert(TABLE_NAME, null, values2);
            dbs.insert(TABLE_NAME, null, values3);
            dbs.insert(TABLE_NAME, null, values4);
            dbs.insert(TABLE_NAME, null, values5);
            dbs.insert(TABLE_NAME, null, values6);
            dbs.insert(TABLE_NAME, null, values7);
            dbs.insert(TABLE_NAME, null, values8);
            dbs.insert(TABLE_NAME, null, values9);
            dbs.insert(TABLE_NAME, null, values10);
       // Toast.makeText(context, "database "+values10, Toast.LENGTH_LONG).show();
        dbs.close();
    }

    /**
     * Getting all labels
     * returns list of labels
     * */
    public List<String> getAllLabels(){
        List<String> list = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase dbs = this.getReadableDatabase();
        Cursor cursor = dbs.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(0));//adding 2nd column data
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        dbs.close();
        // returning lables
        return list;
    }
}