package com.cse.hrcap.RoomLeaveAprSummary;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = LeaveAprSumInfo.class, version = 1, exportSchema = false)
public abstract class LeaveAprSumRoomDB extends RoomDatabase {

    public abstract LeaveAprSumDAO leaveAprSumDAO();


    public static LeaveAprSumRoomDB INSTANCE;

    public  static LeaveAprSumRoomDB getDbInstance(Context context){

        if(INSTANCE ==null){

            INSTANCE = Room.databaseBuilder( context.getApplicationContext(), LeaveAprSumRoomDB.class,"LeaveAprSum.db")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;


    }
}
