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
public class UserRole {
    @Id
    private ObjectId id;

    @DBRef
    @Indexed(unique = true)
    private User user;
    @DBRef
    private List<SysRole> sysRoles;

    private Date datecreated;

    public UserRole() {
    }

    @PersistenceConstructor

    public UserRole(ObjectId id, User user, List<SysRole> sysRoles, Date datecreated) {
        this.id = id;
        this.user = user;
        this.sysRoles = sysRoles;
        this.datecreated = datecreated;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", user=" + user +
                ", sysRoles=" + sysRoles +
                ", datecreated=" + datecreated +
                '}';
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<SysRole> getSysRoles() {
        return sysRoles;
    }

    public void setSysRoles(List<SysRole> sysRoles) {
        this.sysRoles = sysRoles;
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }
}