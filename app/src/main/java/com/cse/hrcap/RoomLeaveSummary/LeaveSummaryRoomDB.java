package com.cse.hrcap.RoomLeaveSummary;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = LeaveSummaryInfo.class, version = 1, exportSchema = false)
public abstract class LeaveSummaryRoomDB extends RoomDatabase {

    public abstract LeaveSummaryDAO leaveSummaryDAO();
}
