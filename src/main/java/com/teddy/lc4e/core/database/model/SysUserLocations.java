package com.teddy.lc4e.core.database.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class SysUserLocations {
    @Id
    private Integer intlocationid;

    private Integer intprovinceid;

    private Integer intcityid;

    private Integer intareaid;

    @PersistenceConstructor
    public SysUserLocations(Integer intlocationid, Integer intprovinceid, Integer intcityid, Integer intareaid) {
        this.intlocationid = intlocationid;
        this.intprovinceid = intprovinceid;
        this.intcityid = intcityid;
        this.intareaid = intareaid;
    }

    public Integer getIntlocationid() {
        return intlocationid;
    }

    public void setIntlocationid(Integer intlocationid) {
        this.intlocationid = intlocationid;
    }

    public Integer getIntprovinceid() {
        return intprovinceid;
    }

    public void setIntprovinceid(Integer intprovinceid) {
        this.intprovinceid = intprovinceid;
    }

    public Integer getIntcityid() {
        return intcityid;
    }

    public void setIntcityid(Integer intcityid) {
        this.intcityid = intcityid;
    }

    public Integer getIntareaid() {
        return intareaid;
    }

    public void setIntareaid(Integer intareaid) {
        this.intareaid = intareaid;
    }
}