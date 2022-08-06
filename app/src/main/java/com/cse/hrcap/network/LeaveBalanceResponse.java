package com.cse.hrcap.network;

public class LeaveBalanceResponse {
    String CompanyId;
    int EmployeeId;
    int LeaveTypeId;
    String LeaveTypeName;
    float TakenLeave;
    float TotalLeave;
    float AvailableLeave;

    public LeaveBalanceResponse(String companyId, int employeeId, int leaveTypeId, String leaveTypeName, float takenLeave, float totalLeave, float availableLeave) {
        CompanyId = companyId;
        EmployeeId = employeeId;
        LeaveTypeId = leaveTypeId;
        LeaveTypeName = leaveTypeName;
        TakenLeave = takenLeave;
        TotalLeave = totalLeave;
        AvailableLeave = availableLeave;
    }

    public String getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(String companyId) {
        CompanyId = companyId;
    }

    public int getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(int employeeId) {
        EmployeeId = employeeId;
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

    public float getTakenLeave() {
        return TakenLeave;
    }

    public void setTakenLeave(float takenLeave) {
        TakenLeave = takenLeave;
    }

    public float getTotalLeave() {
        return TotalLeave;
    }

    public void setTotalLeave(float totalLeave) {
        TotalLeave = totalLeave;
    }

    public float getAvailableLeave() {
        return AvailableLeave;
    }

    public void setAvailableLeave(float availableLeave) {
        AvailableLeave = availableLeave;
    }
}
