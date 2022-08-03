package com.cse.hrcap.ui.loan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class LoansubTypeDbHelper extends SQLiteOpenHelper {
    final int i = 1;
    private static final String DATABASE_NAME = "loansubtype.db";
    private static final String TABLE_NAME = "loan_sub_type";
    private static final String LoansubType = "loansubtype";
    private static final int DATABASE_VERSION_NO = 1;
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + LoansubType + " TEXT);";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private Context context;

    public LoansubTypeDbHelper(@Nullable Context context) {
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

    public void insertRecord(String loantype1, String loantype2, String loantype3, String loantype4, String loantype5, String loantype6, String loantype7) {
        SQLiteDatabase dbs = getWritableDatabase();


        ContentValues values1 = new ContentValues();
        values1.put(LoansubType, loantype1);
        ContentValues values2 = new ContentValues();
        values2.put(LoansubType, loantype2);
        ContentValues values3 = new ContentValues();
        values3.put(LoansubType, loantype3);

        ContentValues values4 = new ContentValues();
        values1.put(LoansubType, loantype4);
        ContentValues values5 = new ContentValues();
        values2.put(LoansubType, loantype5);
        ContentValues values6 = new ContentValues();
        values3.put(LoansubType, loantype6);
        ContentValues values7 = new ContentValues();
        values3.put(LoansubType, loantype7);


//        values.put(Time, time);

        //  Log.e("Values are ", String.valueOf(values));

        dbs.insert(TABLE_NAME, null, values1);
        dbs.insert(TABLE_NAME, null, values2);
        dbs.insert(TABLE_NAME, null, values3);
        dbs.insert(TABLE_NAME, null, values4);
        dbs.insert(TABLE_NAME, null, values5);
        dbs.insert(TABLE_NAME, null, values6);
        dbs.insert(TABLE_NAME, null, values7);


        // Toast.makeText(context, "database "+values10, Toast.LENGTH_LONG).show();
        dbs.close();
    }

    /**
     * Getting all labels
     * returns list of labels
     */
    public List<String> getAllLabels() {
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