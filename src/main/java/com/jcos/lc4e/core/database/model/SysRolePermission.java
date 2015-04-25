package com.jcos.lc4e.core.database.model;

import java.util.Date;

public class SysRolePermission {
    private Integer intid;

    private Integer introleid;

    private Integer intpermissionid;

    private Date datecreated;

    public Integer getIntid() {
        return intid;
    }

    public void setIntid(Integer intid) {
        this.intid = intid;
    }

    public Integer getIntroleid() {
        return introleid;
    }

    public void setIntroleid(Integer introleid) {
        this.introleid = introleid;
    }

    public Integer getIntpermissionid() {
        return intpermissionid;
    }

    public void setIntpermissionid(Integer intpermissionid) {
        this.intpermissionid = intpermissionid;
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }
}