package com.teddy.lc4e.core.database.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class SysCommentLike {
    @Id
    private Integer intid;

    private Integer intcommentid;

    private Integer intuserid;

    private Date datecreatetime;

    @PersistenceConstructor
    public SysCommentLike(Integer intid, Integer intcommentid, Integer intuserid, Date datecreatetime) {
        this.intid = intid;
        this.intcommentid = intcommentid;
        this.intuserid = intuserid;
        this.datecreatetime = datecreatetime;
    }

    public Integer getIntid() {
        return intid;
    }

    public void setIntid(Integer intid) {
        this.intid = intid;
    }

    public Integer getIntcommentid() {
        return intcommentid;
    }

    public void setIntcommentid(Integer intcommentid) {
        this.intcommentid = intcommentid;
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