package com.cse.hrcap.network;

public class LeaveApprovalRequest {

    String CompanyID;
    String Employee;
    String LeaveId;
    String ApprovalStatus;
    String ApprovalReason;
    String Status;

    public LeaveApprovalRequest(String companyID, String employee, String leaveId, String approvalStatus, String approvalReason) {
        CompanyID = companyID;
        Employee = employee;
        LeaveId = leaveId;
        ApprovalStatus = approvalStatus;
        ApprovalReason = approvalReason;
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

    public String getLeaveId() {
        return LeaveId;
    }

    public void setLeaveId(String leaveId) {
        LeaveId = leaveId;
    }

    public String getApprovalStatus() {
        return ApprovalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        ApprovalStatus = approvalStatus;
    }

    public String getApprovalReason() {
        return ApprovalReason;
    }

    public void setApprovalReason(String approvalReason) {
        ApprovalReason = approvalReason;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
