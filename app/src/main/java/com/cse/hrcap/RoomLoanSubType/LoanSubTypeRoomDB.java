package com.cse.hrcap.RoomLoanSubType;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = LoanSubTypeInfo.class, version = 1, exportSchema = false)
public abstract class LoanSubTypeRoomDB extends RoomDatabase {

    public abstract LoanSubTypeDAO loanSubTypeDAO();
}
