package com.teddy.lc4e.core.database.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class UserBasicInfo {
    @Id
    private Integer intid;

    private Integer intuserid;

    @Indexed(unique = true)
    private String strphonenumber;

    private String strsign;

    private String stravatar;

    private Integer intlocationid;

    private Date datebirthday;

    private Date datecreate;

    private Date datemodified;

    @PersistenceConstructor
    public UserBasicInfo(Integer intid, Integer intuserid, String strphonenumber, String strsign, String stravatar, Integer intlocationid, Date datebirthday, Date datecreate, Date datemodified) {
        this.intid = intid;
        this.intuserid = intuserid;
        this.strphonenumber = strphonenumber;
        this.strsign = strsign;
        this.stravatar = stravatar;
        this.intlocationid = intlocationid;
        this.datebirthday = datebirthday;
        this.datecreate = datecreate;
        this.datemodified = datemodified;
    }

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