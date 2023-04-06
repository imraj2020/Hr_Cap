package com.cse.hrcap.network;

import java.util.List;

public class CheckAttendanceResponse {
    String UserId;
    String CheckInOutDate;
    String MonthName;
    String RunBy;
    int LeaveCount;
    int LateCount;
    String TotalOverTime;
    String TotalWorkedTime;
    String CurrentDateTime;
    String EmployeeName;
    String EmployeeDdl;
    String Years;
    String Months;
    int Month;

    private CompanyHeaders CompanyHeader;

    public CompanyHeaders getCompanyHeader() {
        return CompanyHeader;
    }

    public void setCompanyHeader(CompanyHeaders companyHeader) {
        CompanyHeader = companyHeader;
    }

    private List<AttendanceReportList> AttendanceReport = null;

    public List<AttendanceReportList> getAttendanceReport() {
        return AttendanceReport;
    }

    public void setAttendanceReport(List<AttendanceReportList> attendanceReport) {
        AttendanceReport = attendanceReport;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getCheckInOutDate() {
        return CheckInOutDate;
    }

    public void setCheckInOutDate(String checkInOutDate) {
        CheckInOutDate = checkInOutDate;
    }

    public String getMonthName() {
        return MonthName;
    }

    public void setMonthName(String monthName) {
        MonthName = monthName;
    }

    public String getRunBy() {
        return RunBy;
    }

    public void setRunBy(String runBy) {
        RunBy = runBy;
    }

    public int getLeaveCount() {
        return LeaveCount;
    }

    public void setLeaveCount(int leaveCount) {
        LeaveCount = leaveCount;
    }

    public int getLateCount() {
        return LateCount;
    }

    public void setLateCount(int lateCount) {
        LateCount = lateCount;
    }

    public String getTotalOverTime() {
        return TotalOverTime;
    }

    public void setTotalOverTime(String totalOverTime) {
        TotalOverTime = totalOverTime;
    }

    public String getTotalWorkedTime() {
        return TotalWorkedTime;
    }

    public void setTotalWorkedTime(String totalWorkedTime) {
        TotalWorkedTime = totalWorkedTime;
    }

    public String getCurrentDateTime() {
        return CurrentDateTime;
    }

    public void setCurrentDateTime(String currentDateTime) {
        CurrentDateTime = currentDateTime;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
    }

    public String getEmployeeDdl() {
        return EmployeeDdl;
    }

    public void setEmployeeDdl(String employeeDdl) {
        EmployeeDdl = employeeDdl;
    }

    public String getYears() {
        return Years;
    }

    public void setYears(String years) {
        Years = years;
    }

    public String getMonths() {
        return Months;
    }

    public void setMonths(String months) {
        Months = months;
    }

    public int getMonth() {
        return Month;
    }

    public void setMonth(int month) {
        Month = month;
    }



}
