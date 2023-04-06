package com.cse.hrcap.network;

public class CompanyHeaders {
    String Name;
    String Address;
    String AddressDivision;
    String Phone;
    String Email;
    String Website;
    String Logo;
    int CountryId;
    String CompanyTIN;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getAddressDivision() {
        return AddressDivision;
    }

    public void setAddressDivision(String addressDivision) {
        AddressDivision = addressDivision;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public String getLogo() {
        return Logo;
    }

    public void setLogo(String logo) {
        Logo = logo;
    }

    public int getCountryId() {
        return CountryId;
    }

    public void setCountryId(int countryId) {
        CountryId = countryId;
    }

    public String getCompanyTIN() {
        return CompanyTIN;
    }

    public void setCompanyTIN(String companyTIN) {
        CompanyTIN = companyTIN;
    }
}
