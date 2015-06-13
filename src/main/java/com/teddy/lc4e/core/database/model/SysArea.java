package com.teddy.lc4e.core.database.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class SysArea {

    @Id
    private Integer intareaid;
    private Integer intparentareaid;
    @Indexed(unique = true)
    private String strareaabbr;

    private String strareaname;

    private String strareadescription;

    private String strareacss;

    private String strareaicon;

    private Integer isshow;

    private Integer intuserid;

    private Date datecreatetime;

    @PersistenceConstructor
    public SysArea(Integer intareaid, Integer intparentareaid, String strareaabbr, String strareaname, String strareadescription, String strareacss, String strareaicon, Integer isshow, Integer intuserid, Date datecreatetime) {
        this.intareaid = intareaid;
        this.intparentareaid = intparentareaid;
        this.strareaabbr = strareaabbr;
        this.strareaname = strareaname;
        this.strareadescription = strareadescription;
        this.strareacss = strareacss;
        this.strareaicon = strareaicon;
        this.isshow = isshow;
        this.intuserid = intuserid;
        this.datecreatetime = datecreatetime;
    }

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

    public String getStrareadescription() {
        return strareadescription;
    }

    public void setStrareadescription(String strareadescription) {
        this.strareadescription = strareadescription == null ? null : strareadescription.trim();
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

    public Integer getIntuserid() {
        return intuserid;
    }

    public void setIntuserid(Integer intuserid) {
        this.intuserid = intuserid;
    }

    public Date getDatecreatetime() {
        return datecreatetime;
    }

    public void setDatecreatetime(Date datecreatetime) {
        this.datecreatetime = datecreatetime;
    }
}