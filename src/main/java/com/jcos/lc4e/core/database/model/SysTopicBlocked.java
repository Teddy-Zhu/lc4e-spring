package com.jcos.lc4e.core.database.model;

import java.util.Date;

public class SysTopicBlocked {
    private Integer intid;

    private Integer intuserid;

    private Integer intblockedtopicid;

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

    public Integer getIntblockedtopicid() {
        return intblockedtopicid;
    }

    public void setIntblockedtopicid(Integer intblockedtopicid) {
        this.intblockedtopicid = intblockedtopicid;
    }

    public Date getDatecreatetime() {
        return datecreatetime;
    }

    public void setDatecreatetime(Date datecreatetime) {
        this.datecreatetime = datecreatetime;
    }
}