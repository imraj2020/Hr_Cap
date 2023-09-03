package com.cse.hrcap.network;

public class AttandanceRequest {
    String Employee;
    String InOutTime;
    String Type;
    String entryby;
    String companyid;
    String comment;
    String latitude;
    String longitude;
    String address;
    String Status;
    String Message;




    public AttandanceRequest(String employee, String inOutTime, String type, String entryby, String companyid, String comment, String latitude, String longitude, String address) {
        Employee = employee;
        InOutTime = inOutTime;
        Type = type;
        this.entryby = entryby;
        this.companyid = companyid;
        this.comment = comment;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }


    public String getEmployee() {
        return Employee;
    }

    public void setEmployee(String employee) {
        Employee = employee;
    }

    public String getInOutTime() {
        return InOutTime;
    }

    public void setInOutTime(String inOutTime) {
        InOutTime = inOutTime;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getEntryby() {
        return entryby;
    }

    public void setEntryby(String entryby) {
        this.entryby = entryby;
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
