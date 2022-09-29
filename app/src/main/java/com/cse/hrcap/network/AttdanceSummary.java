package com.cse.hrcap.network;

public class AttdanceSummary {

    String CheckInDate;
    String PunchTime;
    String InOut;
    String EntryBy;
    String EntryDate;

    public AttdanceSummary(String checkInDate, String punchTime, String inOut, String entryBy, String entryDate) {
        CheckInDate = checkInDate;
        PunchTime = punchTime;
        InOut = inOut;
        EntryBy = entryBy;
        EntryDate = entryDate;
    }

    public String getCheckInDate() {
        return CheckInDate;
    }

    public void setCheckInDate(String checkInDate) {
        CheckInDate = checkInDate;
    }

    public String getPunchTime() {
        return PunchTime;
    }

    public void setPunchTime(String punchTime) {
        PunchTime = punchTime;
    }

    public String getInOut() {
        return InOut;
    }

    public void setInOut(String inOut) {
        InOut = inOut;
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
