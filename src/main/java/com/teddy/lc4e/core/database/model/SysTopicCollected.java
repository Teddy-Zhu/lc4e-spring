package com.teddy.lc4e.core.database.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class SysTopicCollected {
    @Id
    private Integer intid;

    private Integer intuserid;

    private Integer intcollectedtopicid;

    private Date datecreatetime;

    @PersistenceConstructor
    public SysTopicCollected(Integer intid, Integer intuserid, Integer intcollectedtopicid, Date datecreatetime) {
        this.intid = intid;
        this.intuserid = intuserid;
        this.intcollectedtopicid = intcollectedtopicid;
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

    public Integer getIntcollectedtopicid() {
        return intcollectedtopicid;
    }

    public void setIntcollectedtopicid(Integer intcollectedtopicid) {
        this.intcollectedtopicid = intcollectedtopicid;
    }

    public Date getDatecreatetime() {
        return datecreatetime;
    }

    public void setDatecreatetime(Date datecreatetime) {
        this.datecreatetime = datecreatetime;
    }
}