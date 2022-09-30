package com.cse.hrcap.RoomAtdRegSummary;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class AtdRegInfo {

    @PrimaryKey @NonNull
    String requestid;
    String startdate;
    String enddate;
    String reason;
    String note;
    String fromtime;
    String totime;
    String status;
    String entryby;
    String entrydate;

    public AtdRegInfo(@NonNull String requestid, String startdate, String enddate, String reason, String note, String fromtime, String totime, String status, String entryby, String entrydate) {
        this.requestid = requestid;
        this.startdate = startdate;
        this.enddate = enddate;
        this.reason = reason;
        this.note = note;
        this.fromtime = fromtime;
        this.totime = totime;
        this.status = status;
        this.entryby = entryby;
        this.entrydate = entrydate;
    }

    @NonNull
    public String getRequestid() {
        return requestid;
    }

    public void setRequestid(@NonNull String requestid) {
        this.requestid = requestid;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getFromtime() {
        return fromtime;
    }

    public void setFromtime(String fromtime) {
        this.fromtime = fromtime;
    }

    public String getTotime() {
        return totime;
    }

    public void setTotime(String totime) {
        this.totime = totime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
