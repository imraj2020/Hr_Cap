package com.cse.hrcap.RoomUserInfo;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = UserInfo.class, version = 1, exportSchema = false)
public abstract class UserRoomDB extends RoomDatabase {

    public abstract UserDAO userDAO();

    public static UserRoomDB INSTANCE;

    public  static UserRoomDB getDbInstance(Context context){

        if(INSTANCE ==null){

            INSTANCE = Room.databaseBuilder( context.getApplicationContext(), UserRoomDB.class,"Userinfo.db")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;


    }


}
