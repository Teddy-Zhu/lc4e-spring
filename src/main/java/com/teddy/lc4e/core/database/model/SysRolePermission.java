package com.teddy.lc4e.core.database.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
public class SysRolePermission {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    @DBRef
    private SysRole sysRole;
    @DBRef
    private List<SysPermission> sysPermissions;

    private Date datecreated;

    public SysRolePermission() {
    }

    @PersistenceConstructor

    public SysRolePermission(ObjectId id, SysRole sysRole, List<SysPermission> sysPermissions, Date datecreated) {
        this.id = id;
        this.sysRole = sysRole;
        this.sysPermissions = sysPermissions;
        this.datecreated = datecreated;
    }

    @Override
    public String toString() {
        return "SysRolePermission{" +
                "id=" + id +
                ", sysRole=" + sysRole +
                ", sysPermissions=" + sysPermissions +
                ", datecreated=" + datecreated +
                '}';
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public SysRole getSysRole() {
        return sysRole;
    }

    public void setSysRole(SysRole sysRole) {
        this.sysRole = sysRole;
    }

    public List<SysPermission> getSysPermissions() {
        return sysPermissions;
    }

    public void setSysPermissions(List<SysPermission> sysPermissions) {
        this.sysPermissions = sysPermissions;
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }
}