package com.cse.hrcap.network;

public class RegReasonRequest {

    int Id;
    String Reason;
    String Status;

    public RegReasonRequest(int id, String reason) {
        Id = id;
        Reason = reason;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
