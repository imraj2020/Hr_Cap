package com.cse.hrcap.RoomLeaveSummary;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LeaveSummaryDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertLeaveSummary(LeaveSummaryInfo leaveSummaryInfo);

    @Query("SELECT * FROM LeaveSummaryInfo")
    public List<LeaveSummaryInfo> getAllSummary();


    @Query("SELECT EXISTS(SELECT * FROM LeaveSummaryInfo)")
    public Boolean isExists();

    @Query("DELETE FROM LeaveSummaryInfo")
    void deleteAll();

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
