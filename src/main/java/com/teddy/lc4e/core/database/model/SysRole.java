package com.teddy.lc4e.core.database.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by teddy on 2015/6/12.
 */
@Document
public class SysRole {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    private String roleAbbr;
    private String roleName;
    private String roleDesciption;
    private boolean available;

    public SysRole() {
    }

    @PersistenceConstructor
    public SysRole(ObjectId id, String roleAbbr, String roleName, String roleDesciption, boolean available) {
        this.id = id;
        this.roleAbbr = roleAbbr;
        this.roleName = roleName;
        this.roleDesciption = roleDesciption;
        this.available = available;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "id=" + id +
                ", roleAbbr='" + roleAbbr + '\'' +
                ", roleName='" + roleName + '\'' +
                ", roleDesciption='" + roleDesciption + '\'' +
                ", available=" + available +
                '}';
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getRoleAbbr() {
        return roleAbbr;
    }

    public void setRoleAbbr(String roleAbbr) {
        this.roleAbbr = roleAbbr;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesciption() {
        return roleDesciption;
    }

    public void setRoleDesciption(String roleDesciption) {
        this.roleDesciption = roleDesciption;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
