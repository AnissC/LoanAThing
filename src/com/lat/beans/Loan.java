package com.lat.beans;


public class Loan {


    private Long id;
    private String loanCode;
    private boolean stateLoanCode;
    private String returnCode;
    private boolean stateReturnCode;
    private Apply apply;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode;
    }

    public boolean isStateLoanCode() {
        return stateLoanCode;
    }

    public void setStateLoanCode(boolean stateLoanCode) {
        this.stateLoanCode = stateLoanCode;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public boolean isStateReturnCode() {
        return stateReturnCode;
    }

    public void setStateReturnCode(boolean stateReturnCode) {
        this.stateReturnCode = stateReturnCode;
    }

    public Apply getApply() {
        return apply;
    }

    public void setApply(Apply apply) {
        this.apply = apply;
    }
}
