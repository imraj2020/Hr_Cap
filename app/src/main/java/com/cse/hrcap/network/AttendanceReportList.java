package com.cse.hrcap.network;

public class AttendanceReportList {


    String Date;
    String InTime;
    String OutTime;
    int Status;
    String LateHour;
    String OverTime;
    String TotalWorkedHour;
    String BreakDuration;
    boolean IsHoliday;
    boolean IsWeekend;
    String HoliDayName;
    boolean IsLeave;
    String Leaveype;
    String LeaveReason;
    boolean IsOverTime;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getInTime() {
        return InTime;
    }

    public void setInTime(String inTime) {
        InTime = inTime;
    }

    public String getOutTime() {
        return OutTime;
    }

    public void setOutTime(String outTime) {
        OutTime = outTime;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getLateHour() {
        return LateHour;
    }

    public void setLateHour(String lateHour) {
        LateHour = lateHour;
    }

    public String getOverTime() {
        return OverTime;
    }

    public void setOverTime(String overTime) {
        OverTime = overTime;
    }

    public String getTotalWorkedHour() {
        return TotalWorkedHour;
    }

    public void setTotalWorkedHour(String totalWorkedHour) {
        TotalWorkedHour = totalWorkedHour;
    }

    public String getBreakDuration() {
        return BreakDuration;
    }

    public void setBreakDuration(String breakDuration) {
        BreakDuration = breakDuration;
    }

    public boolean isHoliday() {
        return IsHoliday;
    }

    public void setHoliday(boolean holiday) {
        IsHoliday = holiday;
    }

    public boolean isWeekend() {
        return IsWeekend;
    }

    public void setWeekend(boolean weekend) {
        IsWeekend = weekend;
    }

    public String getHoliDayName() {
        return HoliDayName;
    }

    public void setHoliDayName(String holiDayName) {
        HoliDayName = holiDayName;
    }

    public boolean isLeave() {
        return IsLeave;
    }

    public void setLeave(boolean leave) {
        IsLeave = leave;
    }

    public String getLeaveype() {
        return Leaveype;
    }

    public void setLeaveype(String leaveype) {
        Leaveype = leaveype;
    }

    public String getLeaveReason() {
        return LeaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        LeaveReason = leaveReason;
    }

    public boolean isOverTime() {
        return IsOverTime;
    }

    public void setOverTime(boolean overTime) {
        IsOverTime = overTime;
    }
}
