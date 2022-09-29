package com.cse.hrcap.RoomSelfSummary;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = SelfInfo.class, version = 1, exportSchema = false)
public abstract class SelfRoomDB extends RoomDatabase {

    public abstract SelfDAO selfDAO();
}
