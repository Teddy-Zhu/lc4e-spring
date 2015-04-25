package com.jcos.lc4e.core.database.model;

public class SysRole {
    private Integer introleid;

    private String strroleabbr;

    private String strrolename;

    private String strroledesciption;

    private Integer intavailable;

    public Integer getIntroleid() {
        return introleid;
    }

    public void setIntroleid(Integer introleid) {
        this.introleid = introleid;
    }

    public String getStrroleabbr() {
        return strroleabbr;
    }

    public void setStrroleabbr(String strroleabbr) {
        this.strroleabbr = strroleabbr == null ? null : strroleabbr.trim();
    }

    public String getStrrolename() {
        return strrolename;
    }

    public void setStrrolename(String strrolename) {
        this.strrolename = strrolename == null ? null : strrolename.trim();
    }

    public String getStrroledesciption() {
        return strroledesciption;
    }

    public void setStrroledesciption(String strroledesciption) {
        this.strroledesciption = strroledesciption == null ? null : strroledesciption.trim();
    }

    public Integer getIntavailable() {
        return intavailable;
    }

    public void setIntavailable(Integer intavailable) {
        this.intavailable = intavailable;
    }
}