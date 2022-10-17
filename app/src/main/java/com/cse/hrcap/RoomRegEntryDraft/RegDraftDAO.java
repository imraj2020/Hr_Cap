package com.cse.hrcap.RoomRegEntryDraft;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RegDraftDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertRegDraft(RegDraftInfo regDraftInfo);

    @Query("SELECT * FROM RegDraftInfo")
    public List<RegDraftInfo> getAllRegDraft();

//    @Query("SELECT * FROM RegDraftInfo WHERE leaveid = :myposition")
//
//     public List<RegDraftInfo> getAllDatafromRow(int myposition);


    @Query("SELECT EXISTS(SELECT * FROM RegDraftInfo)")
    public Boolean isExists();

    @Query("DELETE FROM RegDraftInfo")
    void deleteAll();


//
    @Query("DELETE FROM RegDraftInfo WHERE id = :nposition")
    public void deleteRegdraftinfo(int nposition);

}
