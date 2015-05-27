package com.jcos.lc4e.core.database.model;

import java.util.Date;

public class SysCommentLike {
    private Integer intid;

    private Integer intcommentid;

    private Integer intuserid;

    private Date datecreatetime;

    public Integer getIntid() {
        return intid;
    }

    public void setIntid(Integer intid) {
        this.intid = intid;
    }

    public Integer getIntcommentid() {
        return intcommentid;
    }

    public void setIntcommentid(Integer intcommentid) {
        this.intcommentid = intcommentid;
    }

    public Integer getIntuserid() {
        return intuserid;
    }

    public void setIntuserid(Integer intuserid) {
        this.intuserid = intuserid;
    }

    public Date getDatecreatetime() {
        return datecreatetime;
    }

    public void setDatecreatetime(Date datecreatetime) {
        this.datecreatetime = datecreatetime;
    }
}