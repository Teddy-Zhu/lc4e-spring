package com.teddy.lc4e.core.database.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import sun.dc.pr.PRError;

import java.util.Date;

@Document
public class SysArea {

    @Id
    private ObjectId id;

    private ObjectId parentId;

    @Indexed(unique = true)
    private String abbr;

    private String name;

    private String description;

    private String css;

    private String icon;

    @DBRef
    private SysAreaStatus status;

    private Date createTime;

    private Date updateTime;

    @PersistenceConstructor

    public SysArea(ObjectId id, ObjectId parentId, String abbr, String name, String description, String css, String icon, SysAreaStatus status, Date createTime, Date updateTime) {
        this.id = id;
        this.parentId = parentId;
        this.abbr = abbr;
        this.name = name;
        this.description = description;
        this.css = css;
        this.icon = icon;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getParentId() {
        return parentId;
    }

    public void setParentId(ObjectId parentId) {
        this.parentId = parentId;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCss() {
        return css;
    }

    public void setCss(String css) {
        this.css = css;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public SysAreaStatus getStatus() {
        return status;
    }

    public void setStatus(SysAreaStatus status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}