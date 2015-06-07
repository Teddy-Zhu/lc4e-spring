package com.teddy.lc4e.core.database.model;

public class SysLogName {
    private Integer intlognameid;

    private String strlognameabbr;

    private String strlogname;

    public Integer getIntlognameid() {
        return intlognameid;
    }

    public void setIntlognameid(Integer intlognameid) {
        this.intlognameid = intlognameid;
    }

    public String getStrlognameabbr() {
        return strlognameabbr;
    }

    public void setStrlognameabbr(String strlognameabbr) {
        this.strlognameabbr = strlognameabbr == null ? null : strlognameabbr.trim();
    }

    public String getStrlogname() {
        return strlogname;
    }

    public void setStrlogname(String strlogname) {
        this.strlogname = strlogname == null ? null : strlogname.trim();
    }
}