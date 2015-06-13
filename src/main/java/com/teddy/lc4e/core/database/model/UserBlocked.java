package com.teddy.lc4e.core.database.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class UserBlocked {
    @Id
    private Integer intid;

    private Integer intuserid;

    private Integer intblockeduserid;

    private Date datecreatetime;

    @PersistenceConstructor
    public UserBlocked(Integer intid, Integer intuserid, Integer intblockeduserid, Date datecreatetime) {
        this.intid = intid;
        this.intuserid = intuserid;
        this.intblockeduserid = intblockeduserid;
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