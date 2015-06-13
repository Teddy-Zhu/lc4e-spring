package com.teddy.lc4e.core.database.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class SysTopicLike {
    @Id
    private Integer intid;

    private Integer inttopicid;

    private Integer intuserid;

    private Date datecreate;

    @PersistenceConstructor
    public SysTopicLike(Integer intid, Integer inttopicid, Integer intuserid, Date datecreate) {
        this.intid = intid;
        this.inttopicid = inttopicid;
        this.intuserid = intuserid;
        this.datecreate = datecreate;
    }

    public Integer getIntid() {
        return intid;
    }

    public void setIntid(Integer intid) {
        this.intid = intid;
    }

    public Integer getInttopicid() {
        return inttopicid;
    }

    public void setInttopicid(Integer inttopicid) {
        this.inttopicid = inttopicid;
    }

    public Integer getIntuserid() {
        return intuserid;
    }

    public void setIntuserid(Integer intuserid) {
        this.intuserid = intuserid;
    }

    public Date getDatecreate() {
        return datecreate;
    }

    public void setDatecreate(Date datecreate) {
        this.datecreate = datecreate;
    }
}