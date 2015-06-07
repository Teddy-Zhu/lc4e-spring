package com.teddy.lc4e.core.database.model;

import java.util.Date;

public class UserBasicInfo {
    private Integer intid;

    private Integer intuserid;

    private String strphonenumber;

    private String strsign;

    private String stravatar;

    private Integer intlocationid;

    private Date datebirthday;

    private Date datecreate;

    private Date datemodified;

    public Integer getIntid() {
        return intid;
    }

    public void setIntid(Integer intid) {
        this.intid = intid;
    }

    public Integer getIntuserid() {
        return intuserid;
    }

    public void setIntuserid(Integer intuserid) {
        this.intuserid = intuserid;
    }

    public String getStrphonenumber() {
        return strphonenumber;
    }

    public void setStrphonenumber(String strphonenumber) {
        this.strphonenumber = strphonenumber == null ? null : strphonenumber.trim();
    }

    public String getStrsign() {
        return strsign;
    }

    public void setStrsign(String strsign) {
        this.strsign = strsign == null ? null : strsign.trim();
    }

    public String getStravatar() {
        return stravatar;
    }

    public void setStravatar(String stravatar) {
        this.stravatar = stravatar == null ? null : stravatar.trim();
    }

    public Integer getIntlocationid() {
        return intlocationid;
    }

    public void setIntlocationid(Integer intlocationid) {
        this.intlocationid = intlocationid;
    }

    public Date getDatebirthday() {
        return datebirthday;
    }

    public void setDatebirthday(Date datebirthday) {
        this.datebirthday = datebirthday;
    }

    public Date getDatecreate() {
        return datecreate;
    }

    public void setDatecreate(Date datecreate) {
        this.datecreate = datecreate;
    }

    public Date getDatemodified() {
        return datemodified;
    }

    public void setDatemodified(Date datemodified) {
        this.datemodified = datemodified;
    }
}