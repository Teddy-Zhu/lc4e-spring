package com.jcos.lc4e.core.database.model;

import java.util.Date;

public class SysAreaClose {
    private Integer intid;

    private Integer intareaid;

    private Integer intusergroupid;

    private Date datecreatetime;

    public Integer getIntid() {
        return intid;
    }

    public void setIntid(Integer intid) {
        this.intid = intid;
    }

    public Integer getIntareaid() {
        return intareaid;
    }

    public void setIntareaid(Integer intareaid) {
        this.intareaid = intareaid;
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