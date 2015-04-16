package com.jcos.lc4e.core.database.model;

import java.util.Date;

public class UserBlock {
    private Integer intid;

    private Integer intuserid;

    private Integer intblockeduserid;

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

    public Integer getIntblockeduserid() {
        return intblockeduserid;
    }

    public void setIntblockeduserid(Integer intblockeduserid) {
        this.intblockeduserid = intblockeduserid;
    }

    public Date getDatecreatetime() {
        return datecreatetime;
    }

    public void setDatecreatetime(Date datecreatetime) {
        this.datecreatetime = datecreatetime;
    }
}