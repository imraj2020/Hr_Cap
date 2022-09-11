package com.cse.hrcap.RoomHoliday;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = HolidayInfo.class, version = 1, exportSchema = false)
public abstract class HolidayRoomDB extends RoomDatabase {

    public abstract HolidayDAO holidayDAO();
}
