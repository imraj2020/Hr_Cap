package com.cse.hrcap.RoomLeaveDraft;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = LeaveDraftInfo.class, version = 1, exportSchema = false)
public abstract class LeaveDraftRoomDB extends RoomDatabase {

    public abstract LeaveDraftDAO leaveDraftDAO();
}
