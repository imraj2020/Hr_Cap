package com.cse.hrcap.RoomLoanType;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = LoanTypeInfo.class, version = 1, exportSchema = false)
public abstract class LoanTypeRoomDB extends RoomDatabase {

    public abstract LoanTypeDAO loanTypeDAO();
}
