package com.cse.hrcap.RoomRegEntryDraft;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = RegDraftInfo.class, version = 1, exportSchema = false)
public abstract class RegDraftRoomDB extends RoomDatabase {

    public abstract RegDraftDAO regDraftDAO();

    public static RegDraftRoomDB INSTANCE;

    public  static RegDraftRoomDB getDbInstance(Context context){

        if(INSTANCE ==null){

            INSTANCE = Room.databaseBuilder( context.getApplicationContext(), RegDraftRoomDB.class,"RegDraft.db")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;


    }


}
