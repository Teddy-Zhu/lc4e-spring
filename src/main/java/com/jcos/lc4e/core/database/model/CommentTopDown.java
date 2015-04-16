package com.jcos.lc4e.core.database.model;

import java.util.Date;

public class CommentTopDown {
    private Integer intcommentid;

    private Integer intuserid;

    private Integer inttopdown;

    private Date datecreatetime;

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

    public Integer getInttopdown() {
        return inttopdown;
    }

    public void setInttopdown(Integer inttopdown) {
        this.inttopdown = inttopdown;
    }

    public Date getDatecreatetime() {
        return datecreatetime;
    }

    public void setDatecreatetime(Date datecreatetime) {
        this.datecreatetime = datecreatetime;
    }
}