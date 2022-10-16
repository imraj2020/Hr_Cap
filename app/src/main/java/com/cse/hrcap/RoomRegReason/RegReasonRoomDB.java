package com.cse.hrcap.RoomRegReason;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.cse.hrcap.RoomAtdReqAprSummary.AtdRegAprSumRoomDB;

@Database(entities = RegReasonInfo.class, version = 1, exportSchema = false)
public abstract class RegReasonRoomDB extends RoomDatabase {

    public abstract RegReasonDAO regReasonDAO();



    public static RegReasonRoomDB INSTANCE;

    public  static RegReasonRoomDB getDbInstances(Context context){

        if(INSTANCE ==null){

            INSTANCE = Room.databaseBuilder( context.getApplicationContext(),RegReasonRoomDB.class,"RegReason.db")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;


    }
}
