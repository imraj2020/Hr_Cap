package com.cse.hrcap.network;

public class RegularizationApprovalResponse {

    String CompanyID;
    String Employee;
    String MovementId;
    String FromTime;
    String ToTime;
    String Note;
    String EntryFlag;
    String Status;

    public RegularizationApprovalResponse(String companyID, String employee, String movementId, String fromTime, String toTime, String note, String entryFlag) {
        CompanyID = companyID;
        Employee = employee;
        MovementId = movementId;
        FromTime = fromTime;
        ToTime = toTime;
        Note = note;
        EntryFlag = entryFlag;
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

    public String getMovementId() {
        return MovementId;
    }

    public void setMovementId(String movementId) {
        MovementId = movementId;
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

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getEntryFlag() {
        return EntryFlag;
    }

    public void setEntryFlag(String entryFlag) {
        EntryFlag = entryFlag;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
