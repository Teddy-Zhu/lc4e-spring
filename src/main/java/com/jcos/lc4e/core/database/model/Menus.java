package com.jcos.lc4e.core.database.model;

public class Menus {
    private Integer intmenuid;

    private Integer intparentmenuid;

    private Integer intmenulocation;

    private Integer intmenuorderid;

    private String strmenupath;

    private String strmenuname;

    private String strmenucss;

    private String strmenuicon;

    public Integer getIntmenuid() {
        return intmenuid;
    }

    public void setIntmenuid(Integer intmenuid) {
        this.intmenuid = intmenuid;
    }

    public Integer getIntparentmenuid() {
        return intparentmenuid;
    }

    public void setIntparentmenuid(Integer intparentmenuid) {
        this.intparentmenuid = intparentmenuid;
    }

    public Integer getIntmenulocation() {
        return intmenulocation;
    }

    public void setIntmenulocation(Integer intmenulocation) {
        this.intmenulocation = intmenulocation;
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
        this.strmenupath = strmenupath == null ? null : strmenupath.trim();
    }

    public String getStrmenuname() {
        return strmenuname;
    }

    public void setStrmenuname(String strmenuname) {
        this.strmenuname = strmenuname == null ? null : strmenuname.trim();
    }

    public String getStrmenucss() {
        return strmenucss;
    }

    public void setStrmenucss(String strmenucss) {
        this.strmenucss = strmenucss == null ? null : strmenucss.trim();
    }

    public String getStrmenuicon() {
        return strmenuicon;
    }

    public void setStrmenuicon(String strmenuicon) {
        this.strmenuicon = strmenuicon == null ? null : strmenuicon.trim();
    }
}