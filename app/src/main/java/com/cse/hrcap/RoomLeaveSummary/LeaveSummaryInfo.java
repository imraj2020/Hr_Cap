package com.cse.hrcap.RoomLeaveSummary;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LeaveSummaryInfo {

    @PrimaryKey @NonNull
    String leaveid;
    String leavetypename;
    String fromdate;
    String todate;
    String totalday;
    String totalhours;
    String entryby;
    String entrydatetime;
    String leavestatusid;
    String leavestatusname;

    public LeaveSummaryInfo(@NonNull String leaveid, String leavetypename, String fromdate, String todate, String totalday, String totalhours, String entryby, String entrydatetime, String leavestatusid, String leavestatusname) {
        this.leaveid = leaveid;
        this.leavetypename = leavetypename;
        this.fromdate = fromdate;
        this.todate = todate;
        this.totalday = totalday;
        this.totalhours = totalhours;
        this.entryby = entryby;
        this.entrydatetime = entrydatetime;
        this.leavestatusid = leavestatusid;
        this.leavestatusname = leavestatusname;
    }

    @NonNull
    public String getLeaveid() {
        return leaveid;
    }

    public void setLeaveid(@NonNull String leaveid) {
        this.leaveid = leaveid;
    }

    public String getLeavetypename() {
        return leavetypename;
    }

    public void setLeavetypename(String leavetypename) {
        this.leavetypename = leavetypename;
    }

    public String getFromdate() {
        return fromdate;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }

    public String getTotalday() {
        return totalday;
    }

    public void setTotalday(String totalday) {
        this.totalday = totalday;
    }

    public String getTotalhours() {
        return totalhours;
    }

    public void setTotalhours(String totalhours) {
        this.totalhours = totalhours;
    }

    public String getEntryby() {
        return entryby;
    }

    public void setEntryby(String entryby) {
        this.entryby = entryby;
    }

    public String getEntrydatetime() {
        return entrydatetime;
    }

    public void setEntrydatetime(String entrydatetime) {
        this.entrydatetime = entrydatetime;
    }

    public String getLeavestatusid() {
        return leavestatusid;
    }

    public void setLeavestatusid(String leavestatusid) {
        this.leavestatusid = leavestatusid;
    }

    public String getLeavestatusname() {
        return leavestatusname;
    }

    public void setLeavestatusname(String leavestatusname) {
        this.leavestatusname = leavestatusname;
    }
}
