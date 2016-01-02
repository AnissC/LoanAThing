package com.lat.beans;

public class Loan {
    private Integer id;
    private String code;
    private Boolean stateCode;
    private String returnCode;
    private Boolean stateReturnCode;
    private Apply apply;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Apply getApply() { return apply; }

    public void setApply(Apply apply) { this.apply = apply; }
}
