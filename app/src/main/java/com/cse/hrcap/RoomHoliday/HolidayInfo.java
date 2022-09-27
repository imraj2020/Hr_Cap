package com.cse.hrcap.RoomHoliday;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class HolidayInfo {


    @PrimaryKey @NonNull
    int holidayid;
    String companyid;

    String holidayname;
    String shortname;
    String religionspecific;
    int religionid;
    String religionname;
    int typeid;
    String typename;
    String description;
    String active;
    String everyyearsamemonthday;

    public HolidayInfo(int holidayid, String companyid, String holidayname, String shortname, String religionspecific, int religionid, String religionname, int typeid, String typename, String description, String active, String everyyearsamemonthday) {
        this.holidayid = holidayid;
        this.companyid = companyid;
        this.holidayname = holidayname;
        this.shortname = shortname;
        this.religionspecific = religionspecific;
        this.religionid = religionid;
        this.religionname = religionname;
        this.typeid = typeid;
        this.typename = typename;
        this.description = description;
        this.active = active;
        this.everyyearsamemonthday = everyyearsamemonthday;
    }

    public int getHolidayid() {
        return holidayid;
    }

    public void setHolidayid(int holidayid) {
        this.holidayid = holidayid;
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public String getHolidayname() {
        return holidayname;
    }

    public void setHolidayname(String holidayname) {
        this.holidayname = holidayname;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getReligionspecific() {
        return religionspecific;
    }

    public void setReligionspecific(String religionspecific) {
        this.religionspecific = religionspecific;
    }

    public int getReligionid() {
        return religionid;
    }

    public void setReligionid(int religionid) {
        this.religionid = religionid;
    }

    public String getReligionname() {
        return religionname;
    }

    public void setReligionname(String religionname) {
        this.religionname = religionname;
    }

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getEveryyearsamemonthday() {
        return everyyearsamemonthday;
    }

    public void setEveryyearsamemonthday(String everyyearsamemonthday) {
        this.everyyearsamemonthday = everyyearsamemonthday;
    }
}
