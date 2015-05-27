package com.jcos.lc4e.core.database.model;

import java.util.ArrayList;
import java.util.List;

public class SysMenu {
    private Integer intmenuid;

    private Integer intparentmenuid;

    private Integer intmenuorderid;

    private String strmenupath;

    private String strmenuname;

    private String strmenucss;

    private String strmenuicon;

    List<SysMenu> childMenus;

    public SysMenu() {
        this.childMenus = new ArrayList<SysMenu>();
        this.strmenuicon = "";
        this.strmenuname = "";
        this.strmenucss = "basic";
        this.strmenupath = "";
        this.intmenuorderid = 0;
        this.intparentmenuid = 1;
        this.intmenuid = 0;
    }

    public SysMenu(List<SysMenu> childMenus, String strmenuicon, String strmenuname, String strmenucss, String strmenupath, Integer intmenuorderid, Integer intparentmenuid, Integer intmenuid) {
        this.childMenus = childMenus;
        this.strmenuicon = strmenuicon;
        this.strmenuname = strmenuname;
        this.strmenucss = strmenucss;
        this.strmenupath = strmenupath;
        this.intmenuorderid = intmenuorderid;
        this.intparentmenuid = intparentmenuid;
        this.intmenuid = intmenuid;
    }

    public List<SysMenu> getChildMenus() {
        return childMenus;
    }

    public void setChildMenus(List<SysMenu> childMenus) {
        this.childMenus = childMenus;
    }

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