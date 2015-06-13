package com.teddy.lc4e.core.database.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class SysPermission {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    private String permissionAbbr;

    private String permissionName;

    private String permissionDescription;

    private boolean avaliable;

    public SysPermission() {
    }

    @PersistenceConstructor
    public SysPermission(ObjectId id, String permissionAbbr, String permissionName, String permissionDescription, boolean avaliable) {
        this.id = id;
        this.permissionAbbr = permissionAbbr;
        this.permissionName = permissionName;
        this.permissionDescription = permissionDescription;
        this.avaliable = avaliable;
    }

    @Override
    public String toString() {
        return "SysPermission{" +
                "id=" + id +
                ", permissionAbbr='" + permissionAbbr + '\'' +
                ", permissionName='" + permissionName + '\'' +
                ", permissionDescription='" + permissionDescription + '\'' +
                ", avaliable=" + avaliable +
                '}';
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getPermissionAbbr() {
        return permissionAbbr;
    }

    public void setPermissionAbbr(String permissionAbbr) {
        this.permissionAbbr = permissionAbbr;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionDescription() {
        return permissionDescription;
    }

    public void setPermissionDescription(String permissionDescription) {
        this.permissionDescription = permissionDescription;
    }

    public boolean isAvaliable() {
        return avaliable;
    }

    public void setAvaliable(boolean avaliable) {
        this.avaliable = avaliable;
    }
}