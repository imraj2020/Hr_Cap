package com.cse.hrcap.network;

public class AtdRegAprSummary {

    String MovementId;
    String CompanyId;
    int EmpId;
    String EmpCode;
    String FullName;
    String StartDate;
    String EndDate;
    String Reason;
    String Note;
    String FromTime;
    String ToTime;
    String Status;
    String EntryBy;
    String EntryDate;

    public AtdRegAprSummary(String movementId, String companyId, int empId, String empCode, String fullName, String startDate, String endDate, String reason, String note, String fromTime, String toTime, String status, String entryBy, String entryDate) {
        MovementId = movementId;
        CompanyId = companyId;
        EmpId = empId;
        EmpCode = empCode;
        FullName = fullName;
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

    public String getMovementId() {
        return MovementId;
    }

    public void setMovementId(String movementId) {
        MovementId = movementId;
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

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
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
