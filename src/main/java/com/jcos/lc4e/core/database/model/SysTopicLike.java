package com.jcos.lc4e.core.database.model;

import java.util.Date;

public class SysTopicLike {
    private Integer intid;

    private Integer inttopicid;

    private Integer intuserid;

    private Date datecreate;

    public Integer getIntid() {
        return intid;
    }

    public void setIntid(Integer intid) {
        this.intid = intid;
    }

    public Integer getInttopicid() {
        return inttopicid;
    }

    public void setInttopicid(Integer inttopicid) {
        this.inttopicid = inttopicid;
    }

    public Integer getIntuserid() {
        return intuserid;
    }

    public void setIntuserid(Integer intuserid) {
        this.intuserid = intuserid;
    }

    public Date getDatecreate() {
        return datecreate;
    }

    public void setDatecreate(Date datecreate) {
        this.datecreate = datecreate;
    }
}