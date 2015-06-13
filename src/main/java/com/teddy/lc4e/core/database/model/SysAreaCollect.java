package com.teddy.lc4e.core.database.model;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class SysAreaCollect {
    private Integer intid;

    private Integer intuserid;

    private Integer intcollectedareaid;

    private Date datecreatetime;

    @PersistenceConstructor
    public SysAreaCollect(Integer intid, Integer intuserid, Integer intcollectedareaid, Date datecreatetime) {
        this.intid = intid;
        this.intuserid = intuserid;
        this.intcollectedareaid = intcollectedareaid;
        this.datecreatetime = datecreatetime;
    }

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

    public Integer getIntcollectedareaid() {
        return intcollectedareaid;
    }

    public void setIntcollectedareaid(Integer intcollectedareaid) {
        this.intcollectedareaid = intcollectedareaid;
    }

    public Date getDatecreatetime() {
        return datecreatetime;
    }

    public void setDatecreatetime(Date datecreatetime) {
        this.datecreatetime = datecreatetime;
    }
}