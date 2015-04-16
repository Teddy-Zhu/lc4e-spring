package com.jcos.lc4e.core.database.model;

import java.util.Date;

public class Comments {
    private Integer intcommentid;

    private String intarticleid;

    private String intcommenttitle;

    private String intcommentcontent;

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

    public String getIntarticleid() {
        return intarticleid;
    }

    public void setIntarticleid(String intarticleid) {
        this.intarticleid = intarticleid == null ? null : intarticleid.trim();
    }

    public String getIntcommenttitle() {
        return intcommenttitle;
    }

    public void setIntcommenttitle(String intcommenttitle) {
        this.intcommenttitle = intcommenttitle == null ? null : intcommenttitle.trim();
    }

    public String getIntcommentcontent() {
        return intcommentcontent;
    }

    public void setIntcommentcontent(String intcommentcontent) {
        this.intcommentcontent = intcommentcontent == null ? null : intcommentcontent.trim();
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