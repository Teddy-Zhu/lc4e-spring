package com.teddy.lc4e.core.database.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class SysAreaClose {
    @Id
    private Integer intid;

    private Integer intareaid;

    private Integer intusergroupid;

    private Date datecreatetime;
    @PersistenceConstructor
    public SysAreaClose(Integer intid, Integer intareaid, Integer intusergroupid, Date datecreatetime) {
        this.intid = intid;
        this.intareaid = intareaid;
        this.intusergroupid = intusergroupid;
        this.datecreatetime = datecreatetime;
    }

    public Integer getIntid() {
        return intid;
    }

    public void setIntid(Integer intid) {
        this.intid = intid;
    }

    public Integer getIntareaid() {
        return intareaid;
    }

    public void setIntareaid(Integer intareaid) {
        this.intareaid = intareaid;
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