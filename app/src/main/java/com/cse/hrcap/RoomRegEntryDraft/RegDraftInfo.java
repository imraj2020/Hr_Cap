package com.cse.hrcap.RoomRegEntryDraft;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RegDraftInfo {

    String employee;
    String companyid;
    @PrimaryKey(autoGenerate = true)
    int id;
    int spinnervalue;
    String startdate;
    String enddate;
    String fromttime;
    String totime;
    String reason;
    String createdate;
    String notes;
    String fullname;

    public RegDraftInfo(String employee, String companyid, int spinnervalue, String startdate, String enddate, String fromttime, String totime, String reason, String createdate, String notes, String fullname) {
        this.employee = employee;
        this.companyid = companyid;
        this.spinnervalue = spinnervalue;
        this.startdate = startdate;
        this.enddate = enddate;
        this.fromttime = fromttime;
        this.totime = totime;
        this.reason = reason;
        this.createdate = createdate;
        this.notes = notes;
        this.fullname = fullname;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSpinnervalue() {
        return spinnervalue;
    }

    public void setSpinnervalue(int spinnervalue) {
        this.spinnervalue = spinnervalue;
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

    public String getFromttime() {
        return fromttime;
    }

    public void setFromttime(String fromttime) {
        this.fromttime = fromttime;
    }

    public String getTotime() {
        return totime;
    }

    public void setTotime(String totime) {
        this.totime = totime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
