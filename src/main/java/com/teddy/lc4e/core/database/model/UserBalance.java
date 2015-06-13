package com.teddy.lc4e.core.database.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class UserBalance {
    @Id
    private Integer intid;

    private Integer intuserid;

    private Long doubalance;

    private Date datemodifiedtime;

    @PersistenceConstructor
    public UserBalance(Integer intid, Integer intuserid, Long doubalance, Date datemodifiedtime) {
        this.intid = intid;
        this.intuserid = intuserid;
        this.doubalance = doubalance;
        this.datemodifiedtime = datemodifiedtime;
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

    public Long getDoubalance() {
        return doubalance;
    }

    public void setDoubalance(Long doubalance) {
        this.doubalance = doubalance;
    }

    public Date getDatemodifiedtime() {
        return datemodifiedtime;
    }

    public void setDatemodifiedtime(Date datemodifiedtime) {
        this.datemodifiedtime = datemodifiedtime;
    }
}