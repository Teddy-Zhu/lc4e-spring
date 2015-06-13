package com.teddy.lc4e.core.database.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class SysTopicBlocked {
    @Id
    private Integer intid;

    private Integer intuserid;

    private Integer intblockedtopicid;

    private Date datecreatetime;

    @PersistenceConstructor
    public SysTopicBlocked(Integer intid, Integer intuserid, Integer intblockedtopicid, Date datecreatetime) {
        this.intid = intid;
        this.intuserid = intuserid;
        this.intblockedtopicid = intblockedtopicid;
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

    public Integer getIntblockedtopicid() {
        return intblockedtopicid;
    }

    public void setIntblockedtopicid(Integer intblockedtopicid) {
        this.intblockedtopicid = intblockedtopicid;
    }

    public Date getDatecreatetime() {
        return datecreatetime;
    }

    public void setDatecreatetime(Date datecreatetime) {
        this.datecreatetime = datecreatetime;
    }
}