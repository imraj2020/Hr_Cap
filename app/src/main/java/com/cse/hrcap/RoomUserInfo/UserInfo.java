package com.cse.hrcap.RoomUserInfo;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserInfo {


    @PrimaryKey @NonNull
    String employee;
    int employeeid;
    String companyid;
    int designationid;
    String designation;
    String fullname;
    String grade;
    int gradeid;
    String empid;
    String department;
    int departmentid;
    String position;
    int positionid;
    String category;
    int categoryid;
    String firstname;
    String middlename;
    String lastname;
    String prefix;
    String suffix;
    String personalemail;
    String mobilenno;
    String imagetitle;
    String imagepath;
    String joiningdate;
    int costcenterid;
    int paycycleid;
    int locationid;
    int supervisorid;
    String supervisorname;

    public UserInfo(String employee, int employeeid, String companyid, int designationid, String designation, String fullname, String grade, int gradeid, String empid, String department, int departmentid, String position, int positionid, String category, int categoryid, String firstname, String middlename, String lastname, String prefix, String suffix, String personalemail, String mobilenno, String imagetitle, String imagepath, String joiningdate, int costcenterid, int paycycleid, int locationid, int supervisorid, String supervisorname) {
        this.employee = employee;
        this.employeeid = employeeid;
        this.companyid = companyid;
        this.designationid = designationid;
        this.designation = designation;
        this.fullname = fullname;
        this.grade = grade;
        this.gradeid = gradeid;
        this.empid = empid;
        this.department = department;
        this.departmentid = departmentid;
        this.position = position;
        this.positionid = positionid;
        this.category = category;
        this.categoryid = categoryid;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.prefix = prefix;
        this.suffix = suffix;
        this.personalemail = personalemail;
        this.mobilenno = mobilenno;
        this.imagetitle = imagetitle;
        this.imagepath = imagepath;
        this.joiningdate = joiningdate;
        this.costcenterid = costcenterid;
        this.paycycleid = paycycleid;
        this.locationid = locationid;
        this.supervisorid = supervisorid;
        this.supervisorname = supervisorname;
    }


    public int getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(int employeeid) {
        this.employeeid = employeeid;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public int getDesignationid() {
        return designationid;
    }

    public void setDesignationid(int designationid) {
        this.designationid = designationid;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getGradeid() {
        return gradeid;
    }

    public void setGradeid(int gradeid) {
        this.gradeid = gradeid;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(int departmentid) {
        this.departmentid = departmentid;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getPositionid() {
        return positionid;
    }

    public void setPositionid(int positionid) {
        this.positionid = positionid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getPersonalemail() {
        return personalemail;
    }

    public void setPersonalemail(String personalemail) {
        this.personalemail = personalemail;
    }

    public String getMobilenno() {
        return mobilenno;
    }

    public void setMobilenno(String mobilenno) {
        this.mobilenno = mobilenno;
    }

    public String getImagetitle() {
        return imagetitle;
    }

    public void setImagetitle(String imagetitle) {
        this.imagetitle = imagetitle;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public String getJoiningdate() {
        return joiningdate;
    }

    public void setJoiningdate(String joiningdate) {
        this.joiningdate = joiningdate;
    }

    public int getCostcenterid() {
        return costcenterid;
    }

    public void setCostcenterid(int costcenterid) {
        this.costcenterid = costcenterid;
    }

    public int getPaycycleid() {
        return paycycleid;
    }

    public void setPaycycleid(int paycycleid) {
        this.paycycleid = paycycleid;
    }

    public int getLocationid() {
        return locationid;
    }

    public void setLocationid(int locationid) {
        this.locationid = locationid;
    }

    public int getSupervisorid() {
        return supervisorid;
    }

    public void setSupervisorid(int supervisorid) {
        this.supervisorid = supervisorid;
    }

    public String getSupervisorname() {
        return supervisorname;
    }

    public void setSupervisorname(String supervisorname) {
        this.supervisorname = supervisorname;
    }
}
