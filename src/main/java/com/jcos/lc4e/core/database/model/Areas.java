package com.jcos.lc4e.core.database.model;

import java.util.Date;

public class Areas {
    private Integer intareaid;

    private Integer intparentareaid;

    private String strareaabbr;

    private String strareaname;

    private String strareacss;

    private String strareaicon;

    private Integer isshow;

    private Date datecreatetime;

    private Integer intuserid;

    public Integer getIntareaid() {
        return intareaid;
    }

    public void setIntareaid(Integer intareaid) {
        this.intareaid = intareaid;
    }

    public Integer getIntparentareaid() {
        return intparentareaid;
    }

    public void setIntparentareaid(Integer intparentareaid) {
        this.intparentareaid = intparentareaid;
    }

    public String getStrareaabbr() {
        return strareaabbr;
    }

    public void setStrareaabbr(String strareaabbr) {
        this.strareaabbr = strareaabbr == null ? null : strareaabbr.trim();
    }

    public String getStrareaname() {
        return strareaname;
    }

    public void setStrareaname(String strareaname) {
        this.strareaname = strareaname == null ? null : strareaname.trim();
    }

    public String getStrareacss() {
        return strareacss;
    }

    public void setStrareacss(String strareacss) {
        this.strareacss = strareacss == null ? null : strareacss.trim();
    }

    public String getStrareaicon() {
        return strareaicon;
    }

    public void setStrareaicon(String strareaicon) {
        this.strareaicon = strareaicon == null ? null : strareaicon.trim();
    }

    public Integer getIsshow() {
        return isshow;
    }

    public void setIsshow(Integer isshow) {
        this.isshow = isshow;
    }

    public Date getDatecreatetime() {
        return datecreatetime;
    }

    public void setDatecreatetime(Date datecreatetime) {
        this.datecreatetime = datecreatetime;
    }

    public Integer getIntuserid() {
        return intuserid;
    }

    public void setIntuserid(Integer intuserid) {
        this.intuserid = intuserid;
    }
}