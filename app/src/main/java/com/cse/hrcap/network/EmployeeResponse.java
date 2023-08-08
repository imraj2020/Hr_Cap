package com.cse.hrcap.network;

public class EmployeeResponse {

    int Id;
    String EmployeeName;
    String EmpIdAutomatic;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String firstName) {
        EmployeeName = firstName;
    }

    public String getEmpIdAutomatic() {
        return EmpIdAutomatic;
    }

    public void setEmpIdAutomatic(String empIdAutomatic) {
        EmpIdAutomatic = empIdAutomatic;
    }
}
