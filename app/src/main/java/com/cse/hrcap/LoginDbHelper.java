package com.cse.hrcap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class LoginDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "login.db";
    private static final String TABLE_NAME = "login_details";
    private static final String ID = "_id";
    private static final String Username = "username";
    private static final String Password = "password";
    private static final int DATABASE_VERSION_NO = 1;
    //use for --->>> userLogin method
    public  static  final String SELECT_ALL_DATA = "SELECT * FROM "+TABLE_NAME;
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + Username + " VARCHAR(255)," + Password + " VARCHAR(255));";
    private Context context;

    public LoginDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION_NO);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            Toast.makeText(context, "onCreate is called", Toast.LENGTH_SHORT).show();
            db.execSQL(CREATE_TABLE);
        } catch (Exception e) {
            Toast.makeText(context, "Exception : " + e, Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public Cursor alldata(){
        SQLiteDatabase dbs = this.getWritableDatabase();
        Cursor cursor = dbs.rawQuery("select * from login_details" ,null);
        return cursor;
    }

    public void insertRecord(String userid, String password) {

        SQLiteDatabase dbc = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Username, userid);
        values.put(Password, password);
        //  Log.e("Values are ", String.valueOf(values));
        //dbc.insert(TABLE_NAME, null, values);
        dbc.insert(TABLE_NAME, null, values);
       // Toast.makeText(context, "database " + values, Toast.LENGTH_LONG).show();
        dbc.close();
    }

    // userLogin method ..
    public Boolean checkusernamepassword(String userid, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from login_details where username = ? and password = ?", new String[] {userid,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}