package com.cse.hrcap.network;

public class HolidayResponse {
    int HolidayId;
    String CompanyId;
    String HolidayName;
    String ShortName;
    String ReligionSpecific;
    int ReligionId;
    String ReligionName;
    int TypeId;
    String TypeName;
    String Description;
    String Active;
    String EveryYearSameMonthDay;

    public HolidayResponse(int holidayId, String companyId, String holidayName, String shortName, String religionSpecific, int religionId, String religionName, int typeId, String typeName, String description, String active, String everyYearSameMonthDay) {
        HolidayId = holidayId;
        CompanyId = companyId;
        HolidayName = holidayName;
        ShortName = shortName;
        ReligionSpecific = religionSpecific;
        ReligionId = religionId;
        ReligionName = religionName;
        TypeId = typeId;
        TypeName = typeName;
        Description = description;
        Active = active;
        EveryYearSameMonthDay = everyYearSameMonthDay;
    }

    public int getHolidayId() {
        return HolidayId;
    }

    public void setHolidayId(int holidayId) {
        HolidayId = holidayId;
    }

    public String getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(String companyId) {
        CompanyId = companyId;
    }

    public String getHolidayName() {
        return HolidayName;
    }

    public void setHolidayName(String holidayName) {
        HolidayName = holidayName;
    }

    public String getShortName() {
        return ShortName;
    }

    public void setShortName(String shortName) {
        ShortName = shortName;
    }

    public String getReligionSpecific() {
        return ReligionSpecific;
    }

    public void setReligionSpecific(String religionSpecific) {
        ReligionSpecific = religionSpecific;
    }

    public int getReligionId() {
        return ReligionId;
    }

    public void setReligionId(int religionId) {
        ReligionId = religionId;
    }

    public String getReligionName() {
        return ReligionName;
    }

    public void setReligionName(String religionName) {
        ReligionName = religionName;
    }

    public int getTypeId() {
        return TypeId;
    }

    public void setTypeId(int typeId) {
        TypeId = typeId;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getActive() {
        return Active;
    }

    public void setActive(String active) {
        Active = active;
    }

    public String getEveryYearSameMonthDay() {
        return EveryYearSameMonthDay;
    }

    public void setEveryYearSameMonthDay(String everyYearSameMonthDay) {
        EveryYearSameMonthDay = everyYearSameMonthDay;
    }
}
