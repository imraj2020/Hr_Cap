package com.cse.hrcap.RoomRegReason;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RegReasonInfo {

    @PrimaryKey @NonNull
    int id;
    String reason;

    public RegReasonInfo(int id, String reason) {
        this.id = id;
        this.reason = reason;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
