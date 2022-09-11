package com.cse.hrcap.RoomLeaveBalance;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = LeaveBalanceInfo.class, version = 1, exportSchema = false)
public abstract class LeaveBalanceRoomDB extends RoomDatabase {

    public abstract LeaveBalanceDAO leaveBalanceDAO();
}
