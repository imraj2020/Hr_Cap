package com.cse.hrcap.network;

public class AttandanceRequest {
    String Employee;
    String CheckInDate;
    String Time;
    String AttType;
    String CompanyID;
    String latitude;
    String longitude;
    String Address;
    String Status;

    public AttandanceRequest(String employee, String checkInDate, String time, String attType, String companyID, String latitude, String longitude, String address) {
        Employee = employee;
        CheckInDate = checkInDate;
        Time = time;
        AttType = attType;
        CompanyID = companyID;
        this.latitude = latitude;
        this.longitude = longitude;
        Address = address;

    }

    public String getEmployee() {
        return Employee;
    }

    public void setEmployee(String employee) {
        Employee = employee;
    }

    public String getCheckInDate() {
        return CheckInDate;
    }

    public void setCheckInDate(String checkInDate) {
        CheckInDate = checkInDate;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getAttType() {
        return AttType;
    }

    public void setAttType(String attType) {
        AttType = attType;
    }

    public String getCompanyID() {
        return CompanyID;
    }

    public void setCompanyID(String companyID) {
        CompanyID = companyID;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
