package com.cse.hrcap.RoomLeaveAprSummary;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LeaveAprSumInfo {

    @PrimaryKey @NonNull
    String leaveid;
    String companyid;
    int empid;
    String empcode;
    int leavetypeid;
    String leavetypename;
    String fromdate;
    String todate;
    String totalday;
    String fromtime;
    String totime;
    String totalhours;
    int leavestatusid;
    String leavestatusname;
    String fullname;
    int indivrequeststatus;
    String indivrequeststatusname;
    String entryby;
    String entrydate;

    public LeaveAprSumInfo(@NonNull String leaveid, String companyid, int empid, String empcode, int leavetypeid, String leavetypename, String fromdate, String todate, String totalday, String fromtime, String totime, String totalhours, int leavestatusid, String leavestatusname, String fullname, int indivrequeststatus, String indivrequeststatusname, String entryby, String entrydate) {
        this.leaveid = leaveid;
        this.companyid = companyid;
        this.empid = empid;
        this.empcode = empcode;
        this.leavetypeid = leavetypeid;
        this.leavetypename = leavetypename;
        this.fromdate = fromdate;
        this.todate = todate;
        this.totalday = totalday;
        this.fromtime = fromtime;
        this.totime = totime;
        this.totalhours = totalhours;
        this.leavestatusid = leavestatusid;
        this.leavestatusname = leavestatusname;
        this.fullname = fullname;
        this.indivrequeststatus = indivrequeststatus;
        this.indivrequeststatusname = indivrequeststatusname;
        this.entryby = entryby;
        this.entrydate = entrydate;
    }

    @NonNull
    public String getLeaveid() {
        return leaveid;
    }

    public void setLeaveid(@NonNull String leaveid) {
        this.leaveid = leaveid;
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

    public int getLeavetypeid() {
        return leavetypeid;
    }

    public void setLeavetypeid(int leavetypeid) {
        this.leavetypeid = leavetypeid;
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

    public String getTotalhours() {
        return totalhours;
    }

    public void setTotalhours(String totalhours) {
        this.totalhours = totalhours;
    }

    public int getLeavestatusid() {
        return leavestatusid;
    }

    public void setLeavestatusid(int leavestatusid) {
        this.leavestatusid = leavestatusid;
    }

    public String getLeavestatusname() {
        return leavestatusname;
    }

    public void setLeavestatusname(String leavestatusname) {
        this.leavestatusname = leavestatusname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getIndivrequeststatus() {
        return indivrequeststatus;
    }

    public void setIndivrequeststatus(int indivrequeststatus) {
        this.indivrequeststatus = indivrequeststatus;
    }

    public String getIndivrequeststatusname() {
        return indivrequeststatusname;
    }

    public void setIndivrequeststatusname(String indivrequeststatusname) {
        this.indivrequeststatusname = indivrequeststatusname;
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
