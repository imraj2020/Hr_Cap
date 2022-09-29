package com.cse.hrcap.RoomSelfSummary;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SelfInfo {



    String checkindate;
    @PrimaryKey(autoGenerate = true) @NonNull
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    String punchtime;
    String inout;
    String entryby;
    String entrydate;

    public SelfInfo(String checkindate, String punchtime, String inout, String entryby, String entrydate) {
        this.checkindate = checkindate;
        this.punchtime = punchtime;
        this.inout = inout;
        this.entryby = entryby;
        this.entrydate = entrydate;
    }


    public String getCheckindate() {
        return checkindate;
    }

    public void setCheckindate(String checkindate) {
        this.checkindate = checkindate;
    }

    public String getPunchtime() {
        return punchtime;
    }

    public void setPunchtime(String punchtime) {
        this.punchtime = punchtime;
    }

    public String getInout() {
        return inout;
    }

    public void setInout(String inout) {
        this.inout = inout;
    }

    public String getEntryby() {
        return entryby;
    }

    public void setEntryby(String entryby) {
        this.entryby = entryby;
    }

    public String getEntrydate() {
        return entrydate;
    }

    public void setEntrydate(String entrydate) {
        this.entrydate = entrydate;
    }
}
