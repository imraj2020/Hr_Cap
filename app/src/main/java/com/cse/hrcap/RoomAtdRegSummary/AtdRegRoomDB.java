package com.cse.hrcap.RoomAtdRegSummary;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = AtdRegInfo.class, version = 1, exportSchema = false)
public abstract class AtdRegRoomDB extends RoomDatabase {

    public abstract AtdRegDAO atdRegDAO();
}
