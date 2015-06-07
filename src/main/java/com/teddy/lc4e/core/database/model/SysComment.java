package com.teddy.lc4e.core.database.model;

import java.util.Date;

public class SysComment {
    private Integer intcommentid;

    private String inttopicid;

    private String intcommenttitle;

    private String intcommentbody;

    private Integer intuserid;

    private Integer ishide;

    private Integer isdeleted;

    private Date datecreatetime;

    private Date datemodifiedtime;

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