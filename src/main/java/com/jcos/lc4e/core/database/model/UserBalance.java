package com.jcos.lc4e.core.database.model;

import java.util.Date;

public class UserBalance {
    private Integer intid;

    private Integer intuserid;

    private Long doubalance;

    private Date datemodifiedtime;

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

    public Long getDoubalance() {
        return doubalance;
    }

    public void setDoubalance(Long doubalance) {
        this.doubalance = doubalance;
    }

    public Date getDatemodifiedtime() {
        return datemodifiedtime;
    }

    public void setDatemodifiedtime(Date datemodifiedtime) {
        this.datemodifiedtime = datemodifiedtime;
    }
}