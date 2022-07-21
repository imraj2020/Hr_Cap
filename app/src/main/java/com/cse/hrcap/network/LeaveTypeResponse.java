package com.cse.hrcap.network;

import com.google.gson.annotations.SerializedName;

public class LeaveTypeResponse {
    public LeaveTypeResponse(String leaveTypeId, String leaveTypeName, String companyId, String shortName, String description) {
        LeaveTypeId = leaveTypeId;
        LeaveTypeName = leaveTypeName;
        CompanyId = companyId;
        ShortName = shortName;
        Description = description;
    }

    String LeaveTypeId;
    String LeaveTypeName;
    String CompanyId;
    String ShortName;
    String Description;
    @SerializedName("body")
    private String text;

    public String getLeaveTypeId() {
        return LeaveTypeId;
    }

    public void setLeaveTypeId(String leaveTypeId) {
        LeaveTypeId = leaveTypeId;
    }

    public String getLeaveTypeName() {
        return LeaveTypeName;
    }

    public void setLeaveTypeName(String leaveTypeName) {
        LeaveTypeName = leaveTypeName;
    }

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

    public String getText() {
        return text;
    }

    public LeaveTypeResponse() {
    }
}
