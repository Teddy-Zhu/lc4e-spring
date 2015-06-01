package com.jcos.lc4e.core.database.model;

import java.util.Date;

public class SysUserMessage {
    private Integer intmessageid;

    private Integer intuserid;

    private Integer intdestuserid;

    private Integer intread;

    private String strmessagetitle;

    private String strmessagebody;

    private Date datecreatetime;

    private Date datemodifiedtime;

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