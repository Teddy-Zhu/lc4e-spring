package com.teddy.lc4e.core.database.model;

import java.util.Date;

public class UserRole {
    private Integer intid;

    private Integer intuserid;

    private Integer introleid;

    private Date datecreated;

    public Integer getIntid() {
        return intid;
    }

    public void setIntid(Integer intid) {
        this.intid = intid;
    }

    public Integer getIntuserid() {
        return intuserid;
    }

    public void setIntuserid(Integer intuserid) {
        this.intuserid = intuserid;
    }

    public Integer getIntroleid() {
        return introleid;
    }

    public void setIntroleid(Integer introleid) {
        this.introleid = introleid;
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }
}