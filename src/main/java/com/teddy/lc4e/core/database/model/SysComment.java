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

    private Integer ishide;

    private Integer isdeleted;

    private Date datecreatetime;

    private Date datemodifiedtime;

    @PersistenceConstructor
    public SysComment(Integer intcommentid, String inttopicid, String intcommenttitle, String intcommentbody, Integer intuserid, Integer ishide, Integer isdeleted, Date datecreatetime, Date datemodifiedtime) {
        this.intcommentid = intcommentid;
        this.inttopicid = inttopicid;
        this.intcommenttitle = intcommenttitle;
        this.intcommentbody = intcommentbody;
        this.intuserid = intuserid;
        this.ishide = ishide;
        this.isdeleted = isdeleted;
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
        this.inttopicid = inttopicid == null ? null : inttopicid.trim();
    }

    public String getIntcommenttitle() {
        return intcommenttitle;
    }

    public void setIntcommenttitle(String intcommenttitle) {
        this.intcommenttitle = intcommenttitle == null ? null : intcommenttitle.trim();
    }

    public String getIntcommentbody() {
        return intcommentbody;
    }

    public void setIntcommentbody(String intcommentbody) {
        this.intcommentbody = intcommentbody == null ? null : intcommentbody.trim();
    }

    public Integer getIntuserid() {
        return intuserid;
    }

    public void setIntuserid(Integer intuserid) {
        this.intuserid = intuserid;
    }

    public Integer getIshide() {
        return ishide;
    }

    public void setIshide(Integer ishide) {
        this.ishide = ishide;
    }

    public Integer getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(Integer isdeleted) {
        this.isdeleted = isdeleted;
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