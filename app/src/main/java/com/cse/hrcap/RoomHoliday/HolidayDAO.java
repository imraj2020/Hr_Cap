package com.cse.hrcap.RoomHoliday;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HolidayDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertHoliday(HolidayInfo holidayInfo);


    @Query("SELECT EXISTS(SELECT * FROM HolidayInfo)")
    public Boolean isExists();

    @Query("DELETE FROM HolidayInfo")
    void deleteAll();

//    @Query("SELECT name FROM HolidayInfo")
//    public List<String> getAllName();
//
//    @Query("SELECT * FROM HolidayInfo")
//    public List<HolidayInfo> getAllLeave();


//    @Query("UPDATE StudentInfo SET name = :name, subject = :subject WHERE id = :id")
//    public void updateStudentInfo(String name, String subject, int id);
//
//    @Query("DELETE FROM StudentInfo WHERE id = :id")
//    public void deleteStudentInfo(int id);

}
