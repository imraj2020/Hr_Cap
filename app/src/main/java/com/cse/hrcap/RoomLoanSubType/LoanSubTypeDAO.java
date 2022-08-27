package com.cse.hrcap.RoomLoanSubType;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LoanSubTypeDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertLoanSub(LoanSubTypeInfo loanSubTypeInfo);

    @Query("SELECT name FROM LoanSubTypeInfo")
    public List<String> getAllName();

    @Query("SELECT * FROM LoanSubTypeInfo")
    public List<LoanSubTypeInfo> getAllLoan();


//    @Query("UPDATE StudentInfo SET name = :name, subject = :subject WHERE id = :id")
//    public void updateStudentInfo(String name, String subject, int id);
//
//    @Query("DELETE FROM StudentInfo WHERE id = :id")
//    public void deleteStudentInfo(int id);

}
