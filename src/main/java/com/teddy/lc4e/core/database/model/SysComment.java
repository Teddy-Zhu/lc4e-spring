package com.teddy.lc4e.core.database.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class SysComment {
    @Id
    private Integer intcommentid;

    private String inttopicid;

    private String intcommenttitle;

    private String intcommentbody;

    private Integer intuserid;

    private Integer status;

    private Date datecreatetime;

    private Date datemodifiedtime;

    public SysComment() {
    }

    @PersistenceConstructor

    public SysComment(Integer intcommentid, String inttopicid, String intcommenttitle, String intcommentbody, Integer intuserid, Integer status, Date datecreatetime, Date datemodifiedtime) {
        this.intcommentid = intcommentid;
        this.inttopicid = inttopicid;
        this.intcommenttitle = intcommenttitle;
        this.intcommentbody = intcommentbody;
        this.intuserid = intuserid;
        this.status = status;
        this.datecreatetime = datecreatetime;
        this.datemodifiedtime = datemodifiedtime;
    }

    public Integer getIntcommentid() {
        return intcommentid;
    }

    public void setIntcommentid(Integer intcommentid) {
        this.intcommentid = intcommentid;
    }

    public String getInttopicid() {
        return inttopicid;
    }

    public void setInttopicid(String inttopicid) {
        this.inttopicid = inttopicid;
    }

    public String getIntcommenttitle() {
        return intcommenttitle;
    }

    public void setIntcommenttitle(String intcommenttitle) {
        this.intcommenttitle = intcommenttitle;
    }

    public String getIntcommentbody() {
        return intcommentbody;
    }

    public void setIntcommentbody(String intcommentbody) {
        this.intcommentbody = intcommentbody;
    }

    public Integer getIntuserid() {
        return intuserid;
    }

    public void setIntuserid(Integer intuserid) {
        this.intuserid = intuserid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getDatecreatetime() {
        return datecreatetime;
    }

    public void setDatecreatetime(Date datecreatetime) {
        this.datecreatetime = datecreatetime;
    }

    public Date getDatemodifiedtime() {
        return datemodifiedtime;
    }

    public void setDatemodifiedtime(Date datemodifiedtime) {
        this.datemodifiedtime = datemodifiedtime;
    }
}