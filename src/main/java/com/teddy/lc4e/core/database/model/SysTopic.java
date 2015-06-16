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

    private Integer status;

    private Date datecreatetime;

    private Date datemodified;

    public SysTopic() {
    }

    @PersistenceConstructor

    public SysTopic(Integer inttopicid, Integer intareaid, Integer intuserid, String strtopictitle, String strtopicbody, Integer status, Date datecreatetime, Date datemodified) {
        this.inttopicid = inttopicid;
        this.intareaid = intareaid;
        this.intuserid = intuserid;
        this.strtopictitle = strtopictitle;
        this.strtopicbody = strtopicbody;
        this.status = status;
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
        this.strtopictitle = strtopictitle;
    }

    public String getStrtopicbody() {
        return strtopicbody;
    }

    public void setStrtopicbody(String strtopicbody) {
        this.strtopicbody = strtopicbody;
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

    public Date getDatemodified() {
        return datemodified;
    }

    public void setDatemodified(Date datemodified) {
        this.datemodified = datemodified;
    }
}