package com.teddy.lc4e.core.database.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class SysTopicView {
    @Id
    private Integer intid;

    private Integer inttopicid;

    private Integer intviewcount;

    @PersistenceConstructor
    public SysTopicView(Integer intid, Integer inttopicid, Integer intviewcount) {
        this.intid = intid;
        this.inttopicid = inttopicid;
        this.intviewcount = intviewcount;
    }

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

    public Integer getIntviewcount() {
        return intviewcount;
    }

    public void setIntviewcount(Integer intviewcount) {
        this.intviewcount = intviewcount;
    }
}