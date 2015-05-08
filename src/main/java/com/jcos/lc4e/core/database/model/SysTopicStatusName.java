package com.jcos.lc4e.core.database.model;

public class SysTopicStatusName {
    private Integer intstatusid;

    private String strstatusabbr;

    private String strstatusname;

    private String strstatusdesription;

    private String strstatusicon;

    public Integer getIntstatusid() {
        return intstatusid;
    }

    public void setIntstatusid(Integer intstatusid) {
        this.intstatusid = intstatusid;
    }

    public String getStrstatusabbr() {
        return strstatusabbr;
    }

    public void setStrstatusabbr(String strstatusabbr) {
        this.strstatusabbr = strstatusabbr == null ? null : strstatusabbr.trim();
    }

    public String getStrstatusname() {
        return strstatusname;
    }

    public void setStrstatusname(String strstatusname) {
        this.strstatusname = strstatusname == null ? null : strstatusname.trim();
    }

    public String getStrstatusdesription() {
        return strstatusdesription;
    }

    public void setStrstatusdesription(String strstatusdesription) {
        this.strstatusdesription = strstatusdesription == null ? null : strstatusdesription.trim();
    }

    public String getStrstatusicon() {
        return strstatusicon;
    }

    public void setStrstatusicon(String strstatusicon) {
        this.strstatusicon = strstatusicon == null ? null : strstatusicon.trim();
    }
}