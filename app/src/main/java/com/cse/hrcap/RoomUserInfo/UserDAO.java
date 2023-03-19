package com.cse.hrcap.RoomUserInfo;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertUser(UserInfo userInfo);

    @Query("SELECT * FROM UserInfo")
    public List<UserInfo> getAllUser();
//
    @Query("SELECT * FROM UserInfo WHERE employee = :myposition")
     public List<UserInfo> getAllDatafromRow(String myposition);
//
//
//    @Query("SELECT EXISTS(SELECT * FROM UserInfo)")
//    public Boolean isExists();
//
//    @Query("DELETE FROM UserInfo")
//    void deleteAll();
//
//
//
//    @Query("DELETE FROM UserInfo WHERE id = :nposition")
//    public void deleteRegdraftinfo(int nposition);

}
