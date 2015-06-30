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
@CompoundIndex(name = "userRole_sysRoles", def = "{'sysRoles._id':1}", unique = true)
public class UserRole extends BaseModel{
    @Id
    private ObjectId id;

    @DBRef
    @Indexed(unique = true)
    private User user;
    @DBRef
    private List<SysRole> sysRoles;

    public UserRole() {
    }

    @PersistenceConstructor

    public UserRole(Date createTime, Date updateTime, ObjectId id, User user, List<SysRole> sysRoles) {
        super(createTime, updateTime);
        this.id = id;
        this.user = user;
        this.sysRoles = sysRoles;
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
}