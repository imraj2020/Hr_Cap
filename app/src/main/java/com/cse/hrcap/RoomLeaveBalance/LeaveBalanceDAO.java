package com.cse.hrcap.RoomLeaveBalance;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LeaveBalanceDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertLeaveBalance(LeaveBalanceInfo leaveBalanceInfo);

//    @Query("SELECT name FROM LeaveBalanceInfo")
//    public List<String> getAllName();
//
//    @Query("SELECT * FROM LeaveBalanceInfo")
//    public List<LeaveBalanceInfo> getAllLeave();


//    @Query("UPDATE StudentInfo SET name = :name, subject = :subject WHERE id = :id")
//    public void updateStudentInfo(String name, String subject, int id);
//
//    @Query("DELETE FROM StudentInfo WHERE id = :id")
//    public void deleteStudentInfo(int id);

}
