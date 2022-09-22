package com.cse.hrcap.network;

public class AttandanceRegularizationRequest {
    String CompanyID;
    String Employee;
    String StartDate;
    String EndDate;
    String FromTime;
    String ToTime;
    String Reason;
    String Note;
    private String Status;



    public AttandanceRegularizationRequest(String companyID, String employee, String startDate, String endDate, String fromTime, String toTime, String reason, String note) {
        CompanyID = companyID;
        Employee = employee;
        StartDate = startDate;
        EndDate = endDate;
        FromTime = fromTime;
        ToTime = toTime;
        Reason = reason;
        Note = note;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getCompanyID() {
        return CompanyID;
    }

    public void setCompanyID(String companyID) {
        CompanyID = companyID;
    }

    public String getEmployee() {
        return Employee;
    }

    public void setEmployee(String employee) {
        Employee = employee;
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
}
