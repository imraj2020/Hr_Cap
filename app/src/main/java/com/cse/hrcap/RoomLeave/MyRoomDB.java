package com.cse.hrcap.RoomLeave;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = StudentInfo.class, version = 1, exportSchema = false)
public abstract class MyRoomDB extends RoomDatabase {

    public abstract StudentDAO studentDAO();
}
