package com.cse.hrcap.RoomLeaveAprSummary;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LeaveAprSumDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertLeaveAprSummary(LeaveAprSumInfo leaveAprSumInfo);


    @Query("SELECT EXISTS(SELECT * FROM LeaveAprSumInfo)")
    public Boolean isExists();

    @Query("DELETE FROM LeaveAprSumInfo")
    void deleteAll();


    @Query("SELECT * FROM LeaveAprSumInfo")
    public List<LeaveAprSumInfo> getAllleaveAprSummary();

//    @Query("SELECT name FROM HolidayInfo")
//    public List<String> getAllName();
//


//    @Query("UPDATE StudentInfo SET name = :name, subject = :subject WHERE id = :id")
//    public void updateStudentInfo(String name, String subject, int id);
//
//    @Query("DELETE FROM StudentInfo WHERE id = :id")
//    public void deleteStudentInfo(int id);

}
