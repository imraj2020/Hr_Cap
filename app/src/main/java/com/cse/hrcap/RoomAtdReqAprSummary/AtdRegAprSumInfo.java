package com.cse.hrcap.RoomAtdReqAprSummary;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class AtdRegAprSumInfo {

    @PrimaryKey @NonNull
    String movementid;
    String companyid;
    int empid;
    String empcode;
    String fullname;
    String startdate;
    String enddate;
    String reason;
    String note;
    String fromtime;
    String totime;
    String status;
    String entryby;
    String entrydate;

    public AtdRegAprSumInfo(@NonNull String movementid, String companyid, int empid, String empcode, String fullname, String startdate, String enddate, String reason, String note, String fromtime, String totime, String status, String entryby, String entrydate) {
        this.movementid = movementid;
        this.companyid = companyid;
        this.empid = empid;
        this.empcode = empcode;
        this.fullname = fullname;
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
    public String getMovementid() {
        return movementid;
    }

    public void setMovementid(@NonNull String movementid) {
        this.movementid = movementid;
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public int getEmpid() {
        return empid;
    }

    public void setEmpid(int empid) {
        this.empid = empid;
    }

    public String getEmpcode() {
        return empcode;
    }

    public void setEmpcode(String empcode) {
        this.empcode = empcode;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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
