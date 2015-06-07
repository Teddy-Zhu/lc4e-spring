package com.teddy.lc4e.core.database.model;

import java.util.Date;

public class SysAreaCollect {
    private Integer intid;

    private Integer intuserid;

    private Integer intcollectedareaid;

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

    public Integer getIntcollectedareaid() {
        return intcollectedareaid;
    }

    public void setIntcollectedareaid(Integer intcollectedareaid) {
        this.intcollectedareaid = intcollectedareaid;
    }

    public Date getDatecreatetime() {
        return datecreatetime;
    }

    public void setDatecreatetime(Date datecreatetime) {
        this.datecreatetime = datecreatetime;
    }
}