package com.cse.hrcap.RoomAtdRegSummary;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AtdRegDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAtdRegSummary(AtdRegInfo atdRegInfo);


    @Query("SELECT EXISTS(SELECT * FROM AtdRegInfo)")
    public Boolean isExists();

    @Query("DELETE FROM AtdRegInfo")
    void deleteAll();


    @Query("SELECT * FROM AtdRegInfo")
    public List<AtdRegInfo> getAllRegSummary();

//    @Query("SELECT name FROM HolidayInfo")
//    public List<String> getAllName();
//


//    @Query("UPDATE StudentInfo SET name = :name, subject = :subject WHERE id = :id")
//    public void updateStudentInfo(String name, String subject, int id);
//
//    @Query("DELETE FROM StudentInfo WHERE id = :id")
//    public void deleteStudentInfo(int id);

}
