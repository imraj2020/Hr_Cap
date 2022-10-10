package com.cse.hrcap.network;

public class LeaveAprSummary {

    String LeaveId;
    String CompanyId;
    int EmpId;
    String EmpCode;
    int LeaveTypeId;
    String LeaveTypeName;
    String FromDate;
    String ToDate;
    String TotalDay;
    String FromTime;
    String ToTime;
    String TotalHours;
    int Leavestatusid;
    String LeaveStatusName;
    String FullName;
    int IndivRequestStatus;
    String IndivRequestStatusName;
    String EntryBy;
    String EntryDate;

    public LeaveAprSummary(String leaveId, String companyId, int empId, String empCode, int leaveTypeId, String leaveTypeName, String fromDate, String toDate, String totalDay, String fromTime, String toTime, String totalHours, int leavestatusid, String leaveStatusName, String fullName, int indivRequestStatus, String indivRequestStatusName, String entryBy, String entryDate) {
        LeaveId = leaveId;
        CompanyId = companyId;
        EmpId = empId;
        EmpCode = empCode;
        LeaveTypeId = leaveTypeId;
        LeaveTypeName = leaveTypeName;
        FromDate = fromDate;
        ToDate = toDate;
        TotalDay = totalDay;
        FromTime = fromTime;
        ToTime = toTime;
        TotalHours = totalHours;
        Leavestatusid = leavestatusid;
        LeaveStatusName = leaveStatusName;
        FullName = fullName;
        IndivRequestStatus = indivRequestStatus;
        IndivRequestStatusName = indivRequestStatusName;
        EntryBy = entryBy;
        EntryDate = entryDate;
    }

    public String getLeaveId() {
        return LeaveId;
    }

    public void setLeaveId(String leaveId) {
        LeaveId = leaveId;
    }

    public String getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(String companyId) {
        CompanyId = companyId;
    }

    public int getEmpId() {
        return EmpId;
    }

    public void setEmpId(int empId) {
        EmpId = empId;
    }

    public String getEmpCode() {
        return EmpCode;
    }

    public void setEmpCode(String empCode) {
        EmpCode = empCode;
    }

    public int getLeaveTypeId() {
        return LeaveTypeId;
    }

    public void setLeaveTypeId(int leaveTypeId) {
        LeaveTypeId = leaveTypeId;
    }

    public String getLeaveTypeName() {
        return LeaveTypeName;
    }

    public void setLeaveTypeName(String leaveTypeName) {
        LeaveTypeName = leaveTypeName;
    }

    public String getFromDate() {
        return FromDate;
    }

    public void setFromDate(String fromDate) {
        FromDate = fromDate;
    }

    public String getToDate() {
        return ToDate;
    }

    public void setToDate(String toDate) {
        ToDate = toDate;
    }

    public String getTotalDay() {
        return TotalDay;
    }

    public void setTotalDay(String totalDay) {
        TotalDay = totalDay;
    }

    public String getFromTime() {
        return FromTime;
    }

    public void setFromTime(String fromTime) {
        FromTime = fromTime;
    }

    public String getToTime() {
        return ToTime;
    }

    public void setToTime(String toTime) {
        ToTime = toTime;
    }

    public String getTotalHours() {
        return TotalHours;
    }

    public void setTotalHours(String totalHours) {
        TotalHours = totalHours;
    }

    public int getLeavestatusid() {
        return Leavestatusid;
    }

    public void setLeavestatusid(int leavestatusid) {
        Leavestatusid = leavestatusid;
    }

    public String getLeaveStatusName() {
        return LeaveStatusName;
    }

    public void setLeaveStatusName(String leaveStatusName) {
        LeaveStatusName = leaveStatusName;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public int getIndivRequestStatus() {
        return IndivRequestStatus;
    }

    public void setIndivRequestStatus(int indivRequestStatus) {
        IndivRequestStatus = indivRequestStatus;
    }

    public String getIndivRequestStatusName() {
        return IndivRequestStatusName;
    }

    public void setIndivRequestStatusName(String indivRequestStatusName) {
        IndivRequestStatusName = indivRequestStatusName;
    }

    public String getEntryBy() {
        return EntryBy;
    }

    public void setEntryBy(String entryBy) {
        EntryBy = entryBy;
    }

    public String getEntryDate() {
        return EntryDate;
    }

    public void setEntryDate(String entryDate) {
        EntryDate = entryDate;
    }
}
