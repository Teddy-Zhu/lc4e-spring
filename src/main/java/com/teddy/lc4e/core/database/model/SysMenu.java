package com.teddy.lc4e.core.database.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class SysMenu {

    @Id
    private ObjectId id;

    private ObjectId intparentmenuid;

    private Integer intmenuorderid;

    private String strmenupath;

    private String strmenuname;

    private String strmenucss;

    private String strmenuicon;

    List<SysMenu> childMenus;

    public SysMenu() {
    }

    @PersistenceConstructor

    public SysMenu(ObjectId id, ObjectId intparentmenuid, Integer intmenuorderid, String strmenupath, String strmenuname, String strmenucss, String strmenuicon, List<SysMenu> childMenus) {
        this.id = id;
        this.intparentmenuid = intparentmenuid;
        this.intmenuorderid = intmenuorderid;
        this.strmenupath = strmenupath;
        this.strmenuname = strmenuname;
        this.strmenucss = strmenucss;
        this.strmenuicon = strmenuicon;
        this.childMenus = childMenus;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getIntparentmenuid() {
        return intparentmenuid;
    }

    public void setIntparentmenuid(ObjectId intparentmenuid) {
        this.intparentmenuid = intparentmenuid;
    }

    public Integer getIntmenuorderid() {
        return intmenuorderid;
    }

    public void setIntmenuorderid(Integer intmenuorderid) {
        this.intmenuorderid = intmenuorderid;
    }

    public String getStrmenupath() {
        return strmenupath;
    }

    public void setStrmenupath(String strmenupath) {
        this.strmenupath = strmenupath;
    }

    public String getStrmenuname() {
        return strmenuname;
    }

    public void setStrmenuname(String strmenuname) {
        this.strmenuname = strmenuname;
    }

    public String getStrmenucss() {
        return strmenucss;
    }

    public void setStrmenucss(String strmenucss) {
        this.strmenucss = strmenucss;
    }

    public String getStrmenuicon() {
        return strmenuicon;
    }

    public void setStrmenuicon(String strmenuicon) {
        this.strmenuicon = strmenuicon;
    }

    public List<SysMenu> getChildMenus() {
        return childMenus;
    }

    public void setChildMenus(List<SysMenu> childMenus) {
        this.childMenus = childMenus;
    }
}