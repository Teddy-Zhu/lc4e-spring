package com.teddy.lc4e.core.database.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class UserFollowed {
    @Id
    private Integer intid;

    private Integer intuserid;

    private Integer intfolloweduserid;

    private Date datecreatetime;

    @PersistenceConstructor
    public UserFollowed(Integer intid, Integer intuserid, Integer intfolloweduserid, Date datecreatetime) {
        this.intid = intid;
        this.intuserid = intuserid;
        this.intfolloweduserid = intfolloweduserid;
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

    public Integer getIntfolloweduserid() {
        return intfolloweduserid;
    }

    public void setIntfolloweduserid(Integer intfolloweduserid) {
        this.intfolloweduserid = intfolloweduserid;
    }

    public Date getDatecreatetime() {
        return datecreatetime;
    }

    public void setDatecreatetime(Date datecreatetime) {
        this.datecreatetime = datecreatetime;
    }
}