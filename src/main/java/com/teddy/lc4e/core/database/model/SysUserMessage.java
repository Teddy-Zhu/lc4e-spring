package com.teddy.lc4e.core.database.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class SysUserMessage {
    @Id
    private Integer intmessageid;

    private Integer intuserid;

    private Integer intdestuserid;

    private Integer intread;

    private String strmessagetitle;

    private String strmessagebody;

    private Date datecreatetime;

    private Date datemodifiedtime;

    @PersistenceConstructor
    public SysUserMessage(Integer intmessageid, Integer intuserid, Integer intdestuserid, Integer intread, String strmessagetitle, String strmessagebody, Date datecreatetime, Date datemodifiedtime) {
        this.intmessageid = intmessageid;
        this.intuserid = intuserid;
        this.intdestuserid = intdestuserid;
        this.intread = intread;
        this.strmessagetitle = strmessagetitle;
        this.strmessagebody = strmessagebody;
        this.datecreatetime = datecreatetime;
        this.datemodifiedtime = datemodifiedtime;
    }

    public Integer getIntmessageid() {
        return intmessageid;
    }

    public void setIntmessageid(Integer intmessageid) {
        this.intmessageid = intmessageid;
    }

    public Integer getIntuserid() {
        return intuserid;
    }

    public void setIntuserid(Integer intuserid) {
        this.intuserid = intuserid;
    }

    public Integer getIntdestuserid() {
        return intdestuserid;
    }

    public void setIntdestuserid(Integer intdestuserid) {
        this.intdestuserid = intdestuserid;
    }

    public Integer getIntread() {
        return intread;
    }

    public void setIntread(Integer intread) {
        this.intread = intread;
    }

    public String getStrmessagetitle() {
        return strmessagetitle;
    }

    public void setStrmessagetitle(String strmessagetitle) {
        this.strmessagetitle = strmessagetitle == null ? null : strmessagetitle.trim();
    }

    public String getStrmessagebody() {
        return strmessagebody;
    }

    public void setStrmessagebody(String strmessagebody) {
        this.strmessagebody = strmessagebody == null ? null : strmessagebody.trim();
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