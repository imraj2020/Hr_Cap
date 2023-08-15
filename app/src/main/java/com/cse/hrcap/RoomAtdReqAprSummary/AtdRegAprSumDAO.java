package com.cse.hrcap.RoomAtdReqAprSummary;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AtdRegAprSumDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAtdRegAprSummary(AtdRegAprSumInfo atdRegAprsumInfo);


    @Query("SELECT EXISTS(SELECT * FROM AtdRegAprSumInfo)")
    public Boolean isExists();

    @Query("DELETE FROM AtdRegAprSumInfo")
    void deleteAll();


    @Query("SELECT * FROM AtdRegAprSumInfo")
    public List<AtdRegAprSumInfo> getAllRegaprSummary();


    @Query("SELECT * FROM AtdRegAprSumInfo WHERE movementId = :movementId")
    AtdRegAprSumInfo getAtdRegAprSummaryById(int movementId);



    @Update
    void updateAtdRegAprSummary(AtdRegAprSumInfo atdRegAprSumInfo);


    @Query("DELETE FROM AtdRegAprSumInfo WHERE rowid = :position")
    void deleteRowAtdRegApproval(int position);

//    @Query("SELECT name FROM HolidayInfo")
//    public List<String> getAllName();
//


//    @Query("UPDATE StudentInfo SET name = :name, subject = :subject WHERE id = :id")
//    public void updateStudentInfo(String name, String subject, int id);
//
//    @Query("DELETE FROM StudentInfo WHERE id = :id")
//    public void deleteStudentInfo(int id);

}
