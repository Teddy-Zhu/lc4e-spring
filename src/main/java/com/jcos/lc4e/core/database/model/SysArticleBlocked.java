package com.jcos.lc4e.core.database.model;

import java.util.Date;

public class SysArticleBlocked {
    private Integer intid;

    private Integer intuserid;

    private Integer intblockedarticleid;

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

    public Integer getIntblockedarticleid() {
        return intblockedarticleid;
    }

    public void setIntblockedarticleid(Integer intblockedarticleid) {
        this.intblockedarticleid = intblockedarticleid;
    }

    public Date getDatecreatetime() {
        return datecreatetime;
    }

    public void setDatecreatetime(Date datecreatetime) {
        this.datecreatetime = datecreatetime;
    }
}