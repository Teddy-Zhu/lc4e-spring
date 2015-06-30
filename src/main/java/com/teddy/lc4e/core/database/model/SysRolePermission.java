package com.teddy.lc4e.core.database.model;

import com.teddy.lc4e.core.database.basemodel.BaseModel;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
@CompoundIndex(name = "sysRolePermission_sysPermissions", def = "{'sysPermissions._id':1}", unique = true)
public class SysRolePermission extends BaseModel {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    @DBRef
    private SysRole sysRole;
    @DBRef
    private List<SysPermission> sysPermissions;

    public SysRolePermission() {
    }

    @PersistenceConstructor

    public SysRolePermission(ObjectId id, SysRole sysRole, List<SysPermission> sysPermissions, Date createTime, Date updateTime) {
        super(createTime, updateTime);
        this.id = id;
        this.sysRole = sysRole;
        this.sysPermissions = sysPermissions;
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
}