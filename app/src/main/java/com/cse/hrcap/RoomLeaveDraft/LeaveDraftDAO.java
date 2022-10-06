package com.cse.hrcap.RoomLeaveDraft;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LeaveDraftDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertLeaveDraft(LeaveDraftInfo leaveDraftInfo);

    @Query("SELECT * FROM LeaveDraftInfo")
    public List<LeaveDraftInfo> getAllLeaveDraft();


    @Query("SELECT EXISTS(SELECT * FROM LeaveDraftInfo)")
    public Boolean isExists();

    @Query("DELETE FROM LeaveDraftInfo")
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
