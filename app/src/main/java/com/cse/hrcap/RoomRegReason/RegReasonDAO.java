package com.cse.hrcap.RoomRegReason;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RegReasonDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertReason(RegReasonInfo regReasonInfo);

    @Query("SELECT reason FROM RegReasonInfo")
    public List<String> getAllName();

    @Query("SELECT * FROM RegReasonInfo")
    public List<RegReasonInfo> getAllReason();

    @Query("SELECT EXISTS(SELECT * FROM RegReasonInfo)")
    public Boolean isExists();

    @Query("DELETE FROM RegReasonInfo")
    void deleteAll();


//    @Query("UPDATE StudentInfo SET name = :name, subject = :subject WHERE id = :id")
//    public void updateStudentInfo(String name, String subject, int id);
//
//    @Query("DELETE FROM StudentInfo WHERE id = :id")
//    public void deleteStudentInfo(int id);

}
