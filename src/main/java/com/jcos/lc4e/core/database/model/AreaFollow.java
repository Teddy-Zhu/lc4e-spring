package com.jcos.lc4e.core.database.model;

import java.util.Date;

public class AreaFollow {
    private Integer intid;

    private Integer intuserid;

    private Integer intfollowedareaid;

    private Date datecreatetime;

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

    public Integer getIntfollowedareaid() {
        return intfollowedareaid;
    }

    public void setIntfollowedareaid(Integer intfollowedareaid) {
        this.intfollowedareaid = intfollowedareaid;
    }

    public Date getDatecreatetime() {
        return datecreatetime;
    }

    public void setDatecreatetime(Date datecreatetime) {
        this.datecreatetime = datecreatetime;
    }
}