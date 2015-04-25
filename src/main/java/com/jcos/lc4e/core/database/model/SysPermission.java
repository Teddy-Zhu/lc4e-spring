package com.jcos.lc4e.core.database.model;

public class SysPermission {
    private Integer intpermissionid;

    private String strpermissionabbr;

    private String strpermissionname;

    private String strpermissiondescription;

    private Integer intavaliable;

    public Integer getIntpermissionid() {
        return intpermissionid;
    }

    public void setIntpermissionid(Integer intpermissionid) {
        this.intpermissionid = intpermissionid;
    }

    public String getStrpermissionabbr() {
        return strpermissionabbr;
    }

    public void setStrpermissionabbr(String strpermissionabbr) {
        this.strpermissionabbr = strpermissionabbr == null ? null : strpermissionabbr.trim();
    }

    public String getStrpermissionname() {
        return strpermissionname;
    }

    public void setStrpermissionname(String strpermissionname) {
        this.strpermissionname = strpermissionname == null ? null : strpermissionname.trim();
    }

    public String getStrpermissiondescription() {
        return strpermissiondescription;
    }

    public void setStrpermissiondescription(String strpermissiondescription) {
        this.strpermissiondescription = strpermissiondescription == null ? null : strpermissiondescription.trim();
    }

    public Integer getIntavaliable() {
        return intavaliable;
    }

    public void setIntavaliable(Integer intavaliable) {
        this.intavaliable = intavaliable;
    }
}