package com.cse.hrcap.network;

public class LeaveSummary {
    String LeaveId;
    String LeaveTypeName;
    String FromDate;
    String ToDate;
    String TotalDay;
    String TotalHours;
    String EntryBy;
    String EntryDateTime;
    String LeaveStatusid;
    String LeaveStatusName;

    public LeaveSummary(String leaveId, String leaveTypeName, String fromDate, String toDate, String totalDay, String totalHours, String entryBy, String entryDateTime, String leaveStatusid, String leaveStatusName) {
        LeaveId = leaveId;
        LeaveTypeName = leaveTypeName;
        FromDate = fromDate;
        ToDate = toDate;
        TotalDay = totalDay;
        TotalHours = totalHours;
        EntryBy = entryBy;
        EntryDateTime = entryDateTime;
        LeaveStatusid = leaveStatusid;
        LeaveStatusName = leaveStatusName;
    }

    public String getLeaveId() {
        return LeaveId;
    }

    public void setLeaveId(String leaveId) {
        LeaveId = leaveId;
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

    public String getTotalHours() {
        return TotalHours;
    }

    public void setTotalHours(String totalHours) {
        TotalHours = totalHours;
    }

    public String getEntryBy() {
        return EntryBy;
    }

    public void setEntryBy(String entryBy) {
        EntryBy = entryBy;
    }

    public String getEntryDateTime() {
        return EntryDateTime;
    }

    public void setEntryDateTime(String entryDateTime) {
        EntryDateTime = entryDateTime;
    }

    public String getLeaveStatusId() {
        return LeaveStatusid;
    }

    public void setLeaveStatusId(String leaveStatusId) {
        LeaveStatusid = leaveStatusId;
    }

    public String getLeaveStatusName() {
        return LeaveStatusName;
    }

    public void setLeaveStatusName(String leaveStatusName) {
        LeaveStatusName = leaveStatusName;
    }
}
