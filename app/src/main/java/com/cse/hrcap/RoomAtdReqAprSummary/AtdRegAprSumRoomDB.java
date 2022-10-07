package com.cse.hrcap.RoomAtdReqAprSummary;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.cse.hrcap.RoomLeaveDraft.LeaveDraftDAO;
import com.cse.hrcap.RoomLeaveDraft.LeaveDraftRoomDB;

@Database(entities = AtdRegAprSumInfo.class, version = 1, exportSchema = false)
public abstract class AtdRegAprSumRoomDB extends RoomDatabase {

    public abstract AtdRegAprSumDAO atdRegAprSumDAO();


    public static AtdRegAprSumRoomDB INSTANCE;

    public  static AtdRegAprSumRoomDB getDbInstances(Context context){

        if(INSTANCE ==null){

            INSTANCE = Room.databaseBuilder( context.getApplicationContext(),AtdRegAprSumRoomDB.class,"AtdReqAprSum.db")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;


    }
}
