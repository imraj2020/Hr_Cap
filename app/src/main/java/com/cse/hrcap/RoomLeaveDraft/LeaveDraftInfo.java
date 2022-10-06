package com.cse.hrcap.RoomLeaveDraft;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LeaveDraftInfo {

    String employee;
    @PrimaryKey(autoGenerate = true)
    int leaveid;
    int spinnervalue;
    boolean switchvalue;
    String startdate;
    String enddate;
    String starttime;
    String endtime;
    String reason;
    String companyid;
    String createdate;
    String leavetype;
    String daytype;

    public LeaveDraftInfo(String employee, int spinnervalue, boolean switchvalue, String startdate, String enddate, String starttime, String endtime, String reason, String companyid, String createdate, String leavetype, String daytype) {
        this.employee = employee;
        this.spinnervalue = spinnervalue;
        this.switchvalue = switchvalue;
        this.startdate = startdate;
        this.enddate = enddate;
        this.starttime = starttime;
        this.endtime = endtime;
        this.reason = reason;
        this.companyid = companyid;
        this.createdate = createdate;
        this.leavetype = leavetype;
        this.daytype = daytype;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public int getLeaveid() {
        return leaveid;
    }

    public void setLeaveid(int leaveid) {
        this.leaveid = leaveid;
    }

    public int getSpinnervalue() {
        return spinnervalue;
    }

    public void setSpinnervalue(int spinnervalue) {
        this.spinnervalue = spinnervalue;
    }

    public boolean isSwitchvalue() {
        return switchvalue;
    }

    public void setSwitchvalue(boolean switchvalue) {
        this.switchvalue = switchvalue;
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

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getLeavetype() {
        return leavetype;
    }

    public void setLeavetype(String leavetype) {
        this.leavetype = leavetype;
    }

    public String getDaytype() {
        return daytype;
    }

    public void setDaytype(String daytype) {
        this.daytype = daytype;
    }
}
