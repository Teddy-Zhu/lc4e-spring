package com.teddy.lc4e.core.database.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class SysLogName {
    @Id
    private Integer intlognameid;
    @Indexed(unique = true)
    private String strlognameabbr;

    private String strlogname;

    @PersistenceConstructor
    public SysLogName(Integer intlognameid, String strlognameabbr, String strlogname) {
        this.intlognameid = intlognameid;
        this.strlognameabbr = strlognameabbr;
        this.strlogname = strlogname;
    }

    public Integer getIntlognameid() {
        return intlognameid;
    }

    public void setIntlognameid(Integer intlognameid) {
        this.intlognameid = intlognameid;
    }

    public String getStrlognameabbr() {
        return strlognameabbr;
    }

    public void setStrlognameabbr(String strlognameabbr) {
        this.strlognameabbr = strlognameabbr == null ? null : strlognameabbr.trim();
    }

    public String getStrlogname() {
        return strlogname;
    }

    public void setStrlogname(String strlogname) {
        this.strlogname = strlogname == null ? null : strlogname.trim();
    }
}