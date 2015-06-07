package com.teddy.lc4e.core.database.model;

import java.util.Date;

public class SysAreaBlocked {
    private Integer intid;

    private Integer intblockedareaid;

    private Integer intusergroupid;

    private Date datecreatetime;

    public Integer getIntid() {
        return intid;
    }

    public void setIntid(Integer intid) {
        this.intid = intid;
    }

    public Integer getIntblockedareaid() {
        return intblockedareaid;
    }

    public void setIntblockedareaid(Integer intblockedareaid) {
        this.intblockedareaid = intblockedareaid;
    }

    public Integer getIntusergroupid() {
        return intusergroupid;
    }

    public void setIntusergroupid(Integer intusergroupid) {
        this.intusergroupid = intusergroupid;
    }

    public Date getDatecreatetime() {
        return datecreatetime;
    }

    public void setDatecreatetime(Date datecreatetime) {
        this.datecreatetime = datecreatetime;
    }
}