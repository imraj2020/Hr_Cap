package com.cse.hrcap.RoomLeaveDraft;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = LeaveDraftInfo.class, version = 1, exportSchema = false)
public abstract class LeaveDraftRoomDB extends RoomDatabase {

    public abstract LeaveDraftDAO leaveDraftDAO();

    public static LeaveDraftRoomDB INSTANCE;

    public  static LeaveDraftRoomDB getDbInstance(Context context){

        if(INSTANCE ==null){

            INSTANCE = Room.databaseBuilder( context.getApplicationContext(),LeaveDraftRoomDB.class,"LeaveDraft.db")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;


    }


}
