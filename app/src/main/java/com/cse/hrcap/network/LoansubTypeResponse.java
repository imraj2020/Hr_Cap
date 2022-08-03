package com.cse.hrcap.network;

public class LoansubTypeResponse {
    String LoanSubTypeId;
    String CompanyId;
    String LoanSubTypeName;
    String ShortCode;

    public LoansubTypeResponse(String loanSubTypeId, String companyId, String loanSubTypeName, String shortCode) {
        LoanSubTypeId = loanSubTypeId;
        CompanyId = companyId;
        LoanSubTypeName = loanSubTypeName;
        ShortCode = shortCode;
    }

    public String getLoanSubTypeId() {
        return LoanSubTypeId;
    }

    public void setLoanSubTypeId(String loanSubTypeId) {
        LoanSubTypeId = loanSubTypeId;
    }

    public String getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(String companyId) {
        CompanyId = companyId;
    }

    public String getLoanSubTypeName() {
        return LoanSubTypeName;
    }

    public void setLoanSubTypeName(String loanSubTypeName) {
        LoanSubTypeName = loanSubTypeName;
    }

    public String getShortCode() {
        return ShortCode;
    }

    public void setShortCode(String shortCode) {
        ShortCode = shortCode;
    }
}
