package com.teddy.lc4e.core.database.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class SysTopicStatusName {
    @Id
    private Integer intstatusid;

    @Indexed(unique = true)
    private String strstatusabbr;

    private String strstatusname;

    private String strstatusdesription;

    private String strstatusicon;

    @PersistenceConstructor
    public SysTopicStatusName(Integer intstatusid, String strstatusabbr, String strstatusname, String strstatusdesription, String strstatusicon) {
        this.intstatusid = intstatusid;
        this.strstatusabbr = strstatusabbr;
        this.strstatusname = strstatusname;
        this.strstatusdesription = strstatusdesription;
        this.strstatusicon = strstatusicon;
    }

    public Integer getIntstatusid() {
        return intstatusid;
    }

    public void setIntstatusid(Integer intstatusid) {
        this.intstatusid = intstatusid;
    }

    public String getStrstatusabbr() {
        return strstatusabbr;
    }

    public void setStrstatusabbr(String strstatusabbr) {
        this.strstatusabbr = strstatusabbr == null ? null : strstatusabbr.trim();
    }

    public String getStrstatusname() {
        return strstatusname;
    }

    public void setStrstatusname(String strstatusname) {
        this.strstatusname = strstatusname == null ? null : strstatusname.trim();
    }

    public String getStrstatusdesription() {
        return strstatusdesription;
    }

    public void setStrstatusdesription(String strstatusdesription) {
        this.strstatusdesription = strstatusdesription == null ? null : strstatusdesription.trim();
    }

    public String getStrstatusicon() {
        return strstatusicon;
    }

    public void setStrstatusicon(String strstatusicon) {
        this.strstatusicon = strstatusicon == null ? null : strstatusicon.trim();
    }
}