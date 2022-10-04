package com.cse.hrcap.RoomLeaveDraft;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LeaveDraftInfo {

    String employee;
    @PrimaryKey @NonNull
    String leaveid;
    String leavetypename;
    String startdate;
    String enddate;
    String starttime;
    String endtime;
    String reason;



}
