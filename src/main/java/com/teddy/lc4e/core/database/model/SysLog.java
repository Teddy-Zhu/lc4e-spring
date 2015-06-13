package com.teddy.lc4e.core.database.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class SysLog {
    @Id
    private Integer intid;

    private Integer intlognameid;

    private Integer intuserid;

    private Date datecreatetime;

    @PersistenceConstructor
    public SysLog(Integer intid, Integer intlognameid, Integer intuserid, Date datecreatetime) {
        this.intid = intid;
        this.intlognameid = intlognameid;
        this.intuserid = intuserid;
        this.datecreatetime = datecreatetime;
    }

    public Integer getIntid() {
        return intid;
    }

    public void setIntid(Integer intid) {
        this.intid = intid;
    }

    public Integer getIntlognameid() {
        return intlognameid;
    }

    public void setIntlognameid(Integer intlognameid) {
        this.intlognameid = intlognameid;
    }

    public Integer getIntuserid() {
        return intuserid;
    }

    public void setIntuserid(Integer intuserid) {
        this.intuserid = intuserid;
    }

    public Date getDatecreatetime() {
        return datecreatetime;
    }

    public void setDatecreatetime(Date datecreatetime) {
        this.datecreatetime = datecreatetime;
    }
}