package com.jcos.lc4e.core.database.model;

import java.util.Date;

public class SysArticle {
    private Integer intarticleid;

    private Integer intareaid;

    private Integer intuserid;

    private String strarticletitle;

    private String strarticlecontent;

    private Integer ishide;

    private Integer isdeleted;

    private Date datecreatetime;

    private Date datemodified;

    public Integer getIntarticleid() {
        return intarticleid;
    }

    public void setIntarticleid(Integer intarticleid) {
        this.intarticleid = intarticleid;
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

    public String getStrarticletitle() {
        return strarticletitle;
    }

    public void setStrarticletitle(String strarticletitle) {
        this.strarticletitle = strarticletitle == null ? null : strarticletitle.trim();
    }

    public String getStrarticlecontent() {
        return strarticlecontent;
    }

    public void setStrarticlecontent(String strarticlecontent) {
        this.strarticlecontent = strarticlecontent == null ? null : strarticlecontent.trim();
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