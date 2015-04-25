package com.jcos.lc4e.core.database.model;

import java.util.Date;

public class SysArticleTopDown {
    private Integer intid;

    private Integer intarticleid;

    private Integer intuserid;

    private Integer inttopdown;

    private Date datecreate;

    public Integer getIntid() {
        return intid;
    }

    public void setIntid(Integer intid) {
        this.intid = intid;
    }

    public Integer getIntarticleid() {
        return intarticleid;
    }

    public void setIntarticleid(Integer intarticleid) {
        this.intarticleid = intarticleid;
    }

    public Integer getIntuserid() {
        return intuserid;
    }

    public void setIntuserid(Integer intuserid) {
        this.intuserid = intuserid;
    }

    public Integer getInttopdown() {
        return inttopdown;
    }

    public void setInttopdown(Integer inttopdown) {
        this.inttopdown = inttopdown;
    }

    public Date getDatecreate() {
        return datecreate;
    }

    public void setDatecreate(Date datecreate) {
        this.datecreate = datecreate;
    }
}