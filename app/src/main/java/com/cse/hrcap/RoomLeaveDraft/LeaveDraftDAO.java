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

    @Query("SELECT * FROM LeaveDraftInfo WHERE leaveid = :myposition")
   // public int ShowLeaveDraftData(int myposition);
     public List<LeaveDraftInfo> getAllDatafromRow(int myposition);


  //  public void ShowLeaveDraftData(int myposition);
   // public List<LeaveDraftInfo> getAllDatafromRow();


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
    @Query("DELETE FROM LeaveDraftInfo WHERE leaveid = :nposition")
    public void deleteLeavedraftinfo(int nposition);

}
