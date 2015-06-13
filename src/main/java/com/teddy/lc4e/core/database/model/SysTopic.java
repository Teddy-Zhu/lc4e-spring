package com.teddy.lc4e.core.database.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class SysTopic {
    @Id
    private Integer inttopicid;

    private Integer intareaid;

    private Integer intuserid;

    private String strtopictitle;

    private String strtopicbody;

    private Integer ishide;

    private Integer isdeleted;

    private Date datecreatetime;

    private Date datemodified;

    @PersistenceConstructor
    public SysTopic(Integer inttopicid, Integer intareaid, Integer intuserid, String strtopictitle, String strtopicbody, Integer ishide, Integer isdeleted, Date datecreatetime, Date datemodified) {
        this.inttopicid = inttopicid;
        this.intareaid = intareaid;
        this.intuserid = intuserid;
        this.strtopictitle = strtopictitle;
        this.strtopicbody = strtopicbody;
        this.ishide = ishide;
        this.isdeleted = isdeleted;
        this.datecreatetime = datecreatetime;
        this.datemodified = datemodified;
    }

    public Integer getInttopicid() {
        return inttopicid;
    }

    public void setInttopicid(Integer inttopicid) {
        this.inttopicid = inttopicid;
    }

    public Integer getIntareaid() {
        return intareaid;
    }

    public void setIntareaid(Integer intareaid) {
        this.intareaid = intareaid;
    }

    public Integer getIntuserid() {
        return intuserid;
    }

    public void setIntuserid(Integer intuserid) {
        this.intuserid = intuserid;
    }

    public String getStrtopictitle() {
        return strtopictitle;
    }

    public void setStrtopictitle(String strtopictitle) {
        this.strtopictitle = strtopictitle == null ? null : strtopictitle.trim();
    }

    public String getStrtopicbody() {
        return strtopicbody;
    }

    public void setStrtopicbody(String strtopicbody) {
        this.strtopicbody = strtopicbody == null ? null : strtopicbody.trim();
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

    public Date getDatemodified() {
        return datemodified;
    }

    public void setDatemodified(Date datemodified) {
        this.datemodified = datemodified;
    }
}