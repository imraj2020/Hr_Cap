package com.cse.hrcap.network;

public class LoanTypeResponse {
    int LoanTypeId;
    String CompanyId;
    String LoanTypeName;
    String ShortCode;

    public LoanTypeResponse(int loanTypeId, String companyId, String loanTypeName, String shortCode) {
        LoanTypeId = loanTypeId;
        CompanyId = companyId;
        LoanTypeName = loanTypeName;
        ShortCode = shortCode;
    }

    public int getLoanTypeId() {
        return LoanTypeId;
    }

    public void setLoanTypeId(int loanTypeId) {
        LoanTypeId = loanTypeId;
    }

    public String getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(String companyId) {
        CompanyId = companyId;
    }

    public String getLoanTypeName() {
        return LoanTypeName;
    }

    public void setLoanTypeName(String loanTypeName) {
        LoanTypeName = loanTypeName;
    }

    public String getShortCode() {
        return ShortCode;
    }

    public void setShortCode(String shortCode) {
        ShortCode = shortCode;
    }
}
