package com.lat.beans;

public class Loan {
    private Integer id;
    private String loanCode;
    private Boolean stateCode;
    private String returnCode;
    private Boolean stateReturnCode;
    private Integer applyId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode;
    }

    public Boolean getStateCode() {
        return stateCode;
    }

    public void setStateCode(Boolean stateCode) {
        this.stateCode = stateCode;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public Boolean getStateReturnCode() {
        return stateReturnCode;
    }

    public void setStateReturnCode(Boolean stateReturnCode) {
        this.stateReturnCode = stateReturnCode;
    }

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }
}
