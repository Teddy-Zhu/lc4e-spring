package com.jcos.lc4e.core.database.model;

public class UserAuthorization {
    private Integer intid;

    private Integer intuserauthorizationid;

    private String struserauthorizationabbr;

    private String struserauthorizationname;

    private String intuserauthorizationvalue;

    public Integer getIntid() {
        return intid;
    }

    public void setIntid(Integer intid) {
        this.intid = intid;
    }

    public Integer getIntuserauthorizationid() {
        return intuserauthorizationid;
    }

    public void setIntuserauthorizationid(Integer intuserauthorizationid) {
        this.intuserauthorizationid = intuserauthorizationid;
    }

    public String getStruserauthorizationabbr() {
        return struserauthorizationabbr;
    }

    public void setStruserauthorizationabbr(String struserauthorizationabbr) {
        this.struserauthorizationabbr = struserauthorizationabbr == null ? null : struserauthorizationabbr.trim();
    }

    public String getStruserauthorizationname() {
        return struserauthorizationname;
    }

    public void setStruserauthorizationname(String struserauthorizationname) {
        this.struserauthorizationname = struserauthorizationname == null ? null : struserauthorizationname.trim();
    }

    public String getIntuserauthorizationvalue() {
        return intuserauthorizationvalue;
    }

    public void setIntuserauthorizationvalue(String intuserauthorizationvalue) {
        this.intuserauthorizationvalue = intuserauthorizationvalue == null ? null : intuserauthorizationvalue.trim();
    }
}