package com.cse.hrcap.network;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LeaveTypeResponse {
    int LeaveTypeId;
    String LeaveTypeName;

    public String getLeaveTypeName() {
        return LeaveTypeName;
    }

    public void setLeaveTypeName(String leaveTypeName) {
        LeaveTypeName = leaveTypeName;
    }

    String CompanyId;
    String ShortName;
    String Description;

    public String getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(String companyId) {
        CompanyId = companyId;
    }

    public String getShortName() {
        return ShortName;
    }

    public void setShortName(String shortName) {
        ShortName = shortName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getLeaveTypeId() {
        return LeaveTypeId;
    }

    public void setLeaveTypeId(int leaveTypeId) {
        LeaveTypeId = leaveTypeId;
    }

}