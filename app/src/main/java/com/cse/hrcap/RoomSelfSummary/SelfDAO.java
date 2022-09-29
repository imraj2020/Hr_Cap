package com.cse.hrcap.RoomSelfSummary;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cse.hrcap.network.AttdanceSummary;

import java.util.List;

@Dao
public interface SelfDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertSelf(SelfInfo selfInfo);


    @Query("SELECT EXISTS(SELECT * FROM SelfInfo)")
    public Boolean isExists();

    @Query("DELETE FROM SelfInfo")
    void deleteAll();


    @Query("SELECT * FROM SelfInfo")
    public List<SelfInfo> getAllSelfSummary();

//    @Query("SELECT name FROM HolidayInfo")
//    public List<String> getAllName();
//


//    @Query("UPDATE StudentInfo SET name = :name, subject = :subject WHERE id = :id")
//    public void updateStudentInfo(String name, String subject, int id);
//
//    @Query("DELETE FROM StudentInfo WHERE id = :id")
//    public void deleteStudentInfo(int id);

}
