package com.jcos.lc4e.core.database.model;

import java.util.Date;

public class UserBasicInfo {
    private Integer intid;

    private Integer intuserid;

    private String strusername;

    private String strusernick;

    private String stremail;

    private String strpassword;

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

    public String getStrusername() {
        return strusername;
    }

    public void setStrusername(String strusername) {
        this.strusername = strusername == null ? null : strusername.trim();
    }

    public String getStrusernick() {
        return strusernick;
    }

    public void setStrusernick(String strusernick) {
        this.strusernick = strusernick == null ? null : strusernick.trim();
    }

    public String getStremail() {
        return stremail;
    }

    public void setStremail(String stremail) {
        this.stremail = stremail == null ? null : stremail.trim();
    }

    public String getStrpassword() {
        return strpassword;
    }

    public void setStrpassword(String strpassword) {
        this.strpassword = strpassword == null ? null : strpassword.trim();
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