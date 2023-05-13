package com.cse.hrcap.network;

public class EmployeeResponse {

    int Id;
    String FirstName;
    String EmpIdAutomatic;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getEmpIdAutomatic() {
        return EmpIdAutomatic;
    }

    public void setEmpIdAutomatic(String empIdAutomatic) {
        EmpIdAutomatic = empIdAutomatic;
    }
}
