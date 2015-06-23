package com.teddy.lc4e.core.database.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document
public class SysMenu implements Serializable {

    @Transient
    private static final long serialVersionUID = 201606140748327L;

    @Id
    private ObjectId id;

    private ObjectId parentId;

    private Integer order;

    private String path;

    private String name;

    private String css;

    private String icon;

    private Date createTime;

    private Date updateTime;

    @Transient
    List<SysMenu> childMenus = new ArrayList<SysMenu>();

    public SysMenu() {
    }

    public SysMenu(ObjectId id, ObjectId parentId, Integer order, String path, String name, String css, String icon, Date createTime) {
        this.id = id;
        this.parentId = parentId;
        this.order = order;
        this.path = path;
        this.name = name;
        this.css = css;
        this.icon = icon;
        this.createTime = createTime;
    }

    @PersistenceConstructor

    public SysMenu(ObjectId id, ObjectId parentId, Integer order, String path, String name, String css, String icon, Date createTime, Date updateTime) {
        this.id = id;
        this.parentId = parentId;
        this.order = order;
        this.path = path;
        this.name = name;
        this.css = css;
        this.icon = icon;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<SysMenu> getChildMenus() {
        return childMenus;
    }

    public void setChildMenus(List<SysMenu> childMenus) {
        this.childMenus = childMenus;
    }
}