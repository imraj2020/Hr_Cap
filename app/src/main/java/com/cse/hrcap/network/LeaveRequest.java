package com.cse.hrcap.network;

public class LeaveRequest {
    String Employee;
    String LeaveType;
    String DayType;
    String StartDate;
    String EndDate;
    String Reason;
    String StartTime;
    String EndTime;
    String CompanyID;
    private String Status;
    private String LeaveId;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getLeaveId() {
        return LeaveId;
    }

    public void setLeaveId(String leaveId) {
        LeaveId = leaveId;
    }

    private boolean Success;
    private String Status_Message;



    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public String getStatus_Message() {
        return Status_Message;
    }

    public void setStatus_Message(String status_Message) {
        Status_Message = status_Message;
    }

    public LeaveRequest(String employee, String leaveType, String dayType, String startDate, String endDate, String reason, String startTime, String endTime, String companyID) {
        Employee = employee;
        LeaveType = leaveType;
        DayType = dayType;
        StartDate = startDate;
        EndDate = endDate;
        Reason = reason;
        StartTime = startTime;
        EndTime = endTime;
        CompanyID = companyID;
    }

    public String getEmployee() {
        return Employee;
    }

    public void setEmployee(String employee) {
        Employee = employee;
    }

    public String getLeaveType() {
        return LeaveType;
    }

    public void setLeaveType(String leaveType) {
        LeaveType = leaveType;
    }

    public String getDayType() {
        return DayType;
    }

    public void setDayType(String dayType) {
        DayType = dayType;
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

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getCompanyID() {
        return CompanyID;
    }

    public void setCompanyID(String companyID) {
        CompanyID = companyID;
    }
}
