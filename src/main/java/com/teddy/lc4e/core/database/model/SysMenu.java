package com.teddy.lc4e.core.database.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Document
public class SysMenu implements Serializable {

    @Transient
    private static final long serialVersionUID = 20160614074832L;

    @Id
    private ObjectId id;

    private ObjectId intParentMenuId;

    private Integer intMenuOrderId;

    private String strMenuPath;

    private String strMenuName;

    private String strMenuCss;

    private String strMenuIcon;

    @Transient
    List<SysMenu> childMenus = new ArrayList<SysMenu>();

    public SysMenu() {
    }

    @PersistenceConstructor

    public SysMenu(ObjectId id, ObjectId intParentMenuId, Integer intMenuOrderId, String strMenuPath, String strMenuName, String strMenuCss, String strMenuIcon) {
        this.id = id;
        this.intParentMenuId = intParentMenuId;
        this.intMenuOrderId = intMenuOrderId;
        this.strMenuPath = strMenuPath;
        this.strMenuName = strMenuName;
        this.strMenuCss = strMenuCss;
        this.strMenuIcon = strMenuIcon;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getIntParentMenuId() {
        return intParentMenuId;
    }

    public void setIntParentMenuId(ObjectId intParentMenuId) {
        this.intParentMenuId = intParentMenuId;
    }

    public Integer getIntMenuOrderId() {
        return intMenuOrderId;
    }

    public void setIntMenuOrderId(Integer intMenuOrderId) {
        this.intMenuOrderId = intMenuOrderId;
    }

    public String getStrMenuPath() {
        return strMenuPath;
    }

    public void setStrMenuPath(String strMenuPath) {
        this.strMenuPath = strMenuPath;
    }

    public String getStrMenuName() {
        return strMenuName;
    }

    public void setStrMenuName(String strMenuName) {
        this.strMenuName = strMenuName;
    }

    public String getStrMenuCss() {
        return strMenuCss;
    }

    public void setStrMenuCss(String strMenuCss) {
        this.strMenuCss = strMenuCss;
    }

    public String getStrMenuIcon() {
        return strMenuIcon;
    }

    public void setStrMenuIcon(String strMenuIcon) {
        this.strMenuIcon = strMenuIcon;
    }

    public List<SysMenu> getChildMenus() {
        return childMenus;
    }

    public void setChildMenus(List<SysMenu> childMenus) {
        this.childMenus = childMenus;
    }
}