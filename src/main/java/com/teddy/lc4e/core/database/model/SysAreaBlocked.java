package com.teddy.lc4e.core.database.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class SysAreaBlocked {
    @Id
    private Integer intid;

    private Integer intblockedareaid;

    private Integer intusergroupid;

    private Date datecreatetime;
    @PersistenceConstructor
    public SysAreaBlocked(Integer intid, Integer intblockedareaid, Integer intusergroupid, Date datecreatetime) {
        this.intid = intid;
        this.intblockedareaid = intblockedareaid;
        this.intusergroupid = intusergroupid;
        this.datecreatetime = datecreatetime;
    }

    public Integer getIntid() {
        return intid;
    }

    public void setIntid(Integer intid) {
        this.intid = intid;
    }

    public Integer getIntblockedareaid() {
        return intblockedareaid;
    }

    public void setIntblockedareaid(Integer intblockedareaid) {
        this.intblockedareaid = intblockedareaid;
    }

    public Integer getIntusergroupid() {
        return intusergroupid;
    }

    public void setIntusergroupid(Integer intusergroupid) {
        this.intusergroupid = intusergroupid;
    }

    public Date getDatecreatetime() {
        return datecreatetime;
    }

    public void setDatecreatetime(Date datecreatetime) {
        this.datecreatetime = datecreatetime;
    }
}