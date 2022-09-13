package com.cse.hrcap.RoomLoanType;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LoanTypeDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertLoan(LoanTypeInfo loanTypeInfo);

    @Query("SELECT name FROM LoanTypeInfo")
    public List<String> getAllName();

    @Query("SELECT * FROM LoanTypeInfo")
    public List<LoanTypeInfo> getAllLoanSub();

    @Query("SELECT EXISTS(SELECT * FROM LoanTypeInfo)")
    public Boolean isExists();

    @Query("DELETE FROM LoanTypeInfo")
    void deleteAll();


//    @Query("UPDATE StudentInfo SET name = :name, subject = :subject WHERE id = :id")
//    public void updateStudentInfo(String name, String subject, int id);
//
//    @Query("DELETE FROM StudentInfo WHERE id = :id")
//    public void deleteStudentInfo(int id);

}
