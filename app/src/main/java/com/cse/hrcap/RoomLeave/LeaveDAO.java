package com.cse.hrcap.RoomLeave;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LeaveDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertLeave(LeaveInfo leaveInfo);

    @Query("SELECT name FROM LeaveInfo")
    public List<String> getAllName();

    @Query("SELECT * FROM LeaveInfo")
    public List<LeaveInfo> getAllLeave();

    @Query("SELECT EXISTS(SELECT * FROM LeaveInfo)")
    public Boolean isExists();




    // so far everthing okay


//    @Query("UPDATE StudentInfo SET name = :name, subject = :subject WHERE id = :id")
//    public void updateStudentInfo(String name, String subject, int id);
//
//    @Query("DELETE FROM LeaveInfo WHERE id = :id")
//    public void deleteStudentInfo(int id);
   @Query("DELETE FROM LeaveInfo")
   void deleteAll();

}
