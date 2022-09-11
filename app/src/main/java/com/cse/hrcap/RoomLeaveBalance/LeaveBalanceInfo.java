package com.cse.hrcap.RoomLeaveBalance;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LeaveBalanceInfo {

    //@PrimaryKey (autoGenerate = true)
    String companyid;
    int employeeid;
    int leavetypeid;
    @PrimaryKey @NonNull
    String leavetypename;
    float takenleave;
    float totalleave;
    float availableleave;

    public LeaveBalanceInfo(String companyid, int employeeid, int leavetypeid, @NonNull String leavetypename, float takenleave, float totalleave, float availableleave) {
        this.companyid = companyid;
        this.employeeid = employeeid;
        this.leavetypeid = leavetypeid;
        this.leavetypename = leavetypename;
        this.takenleave = takenleave;
        this.totalleave = totalleave;
        this.availableleave = availableleave;
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public int getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(int employeeid) {
        this.employeeid = employeeid;
    }

    public int getLeavetypeid() {
        return leavetypeid;
    }

    public void setLeavetypeid(int leavetypeid) {
        this.leavetypeid = leavetypeid;
    }

    @NonNull
    public String getLeavetypename() {
        return leavetypename;
    }

    public void setLeavetypename(@NonNull String leavetypename) {
        this.leavetypename = leavetypename;
    }

    public float getTakenleave() {
        return takenleave;
    }

    public void setTakenleave(float takenleave) {
        this.takenleave = takenleave;
    }

    public float getTotalleave() {
        return totalleave;
    }

    public void setTotalleave(float totalleave) {
        this.totalleave = totalleave;
    }

    public float getAvailableleave() {
        return availableleave;
    }

    public void setAvailableleave(float availableleave) {
        this.availableleave = availableleave;
    }
}
