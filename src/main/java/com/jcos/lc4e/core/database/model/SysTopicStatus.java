package com.jcos.lc4e.core.database.model;

import java.util.Date;

public class SysTopicStatus {
    private Integer intid;

    private Integer inttopicid;

    private Integer intstatusid;

    private Date datecreatetime;

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

    public Integer getIntstatusid() {
        return intstatusid;
    }

    public void setIntstatusid(Integer intstatusid) {
        this.intstatusid = intstatusid;
    }

    public Date getDatecreatetime() {
        return datecreatetime;
    }

    public void setDatecreatetime(Date datecreatetime) {
        this.datecreatetime = datecreatetime;
    }
}