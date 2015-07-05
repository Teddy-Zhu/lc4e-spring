package com.teddy.lc4e.core.database.model;

import com.teddy.lc4e.core.database.basemodel.BaseModel;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class SysArea extends BaseModel {

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

    @PersistenceConstructor
    public SysArea(Date createTime, Date updateTime, ObjectId id, ObjectId parentId, String abbr, String name, String description, String css, String icon, SysAreaStatus status) {
        super(createTime, updateTime);
        this.id = id;
        this.parentId = parentId;
        this.abbr = abbr;
        this.name = name;
        this.description = description;
        this.css = css;
        this.icon = icon;
        this.status = status;
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
}