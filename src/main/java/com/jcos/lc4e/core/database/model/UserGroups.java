package com.jcos.lc4e.core.database.model;

public class UserGroups {
    private Integer intusergroupid;

    private String strusergroupabbr;

    private String strusergroupname;

    private Integer intuserauthorizationid;

    private Integer intauthorizationlevel;

    public Integer getIntusergroupid() {
        return intusergroupid;
    }

    public void setIntusergroupid(Integer intusergroupid) {
        this.intusergroupid = intusergroupid;
    }

    public String getStrusergroupabbr() {
        return strusergroupabbr;
    }

    public void setStrusergroupabbr(String strusergroupabbr) {
        this.strusergroupabbr = strusergroupabbr == null ? null : strusergroupabbr.trim();
    }

    public String getStrusergroupname() {
        return strusergroupname;
    }

    public void setStrusergroupname(String strusergroupname) {
        this.strusergroupname = strusergroupname == null ? null : strusergroupname.trim();
    }

    public Integer getIntuserauthorizationid() {
        return intuserauthorizationid;
    }

    public void setIntuserauthorizationid(Integer intuserauthorizationid) {
        this.intuserauthorizationid = intuserauthorizationid;
    }

    public Integer getIntauthorizationlevel() {
        return intauthorizationlevel;
    }

    public void setIntauthorizationlevel(Integer intauthorizationlevel) {
        this.intauthorizationlevel = intauthorizationlevel;
    }
}