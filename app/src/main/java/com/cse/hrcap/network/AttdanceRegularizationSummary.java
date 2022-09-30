package com.cse.hrcap.network;

public class AttdanceRegularizationSummary {

    String RequestId;
    String StartDate;
    String EndDate;
    String Reason;
    String Note;
    String FromTime;
    String ToTime;
    String Status;
    String EntryBy;
    String EntryDate;

    public AttdanceRegularizationSummary(String requestId, String startDate, String endDate, String reason, String note, String fromTime, String toTime, String status, String entryBy, String entryDate) {
        RequestId = requestId;
        StartDate = startDate;
        EndDate = endDate;
        Reason = reason;
        Note = note;
        FromTime = fromTime;
        ToTime = toTime;
        Status = status;
        EntryBy = entryBy;
        EntryDate = entryDate;
    }

    public String getRequestId() {
        return RequestId;
    }

    public void setRequestId(String requestId) {
        RequestId = requestId;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
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

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
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
