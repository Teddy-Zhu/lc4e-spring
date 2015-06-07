package com.teddy.lc4e.core.database.model;

import java.util.Date;

public class SysTopicCollected {
    private Integer intid;

    private Integer intuserid;

    private Integer intcollectedtopicid;

    private Date datecreatetime;

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

    public Integer getIntcollectedtopicid() {
        return intcollectedtopicid;
    }

    public void setIntcollectedtopicid(Integer intcollectedtopicid) {
        this.intcollectedtopicid = intcollectedtopicid;
    }

    public Date getDatecreatetime() {
        return datecreatetime;
    }

    public void setDatecreatetime(Date datecreatetime) {
        this.datecreatetime = datecreatetime;
    }
}