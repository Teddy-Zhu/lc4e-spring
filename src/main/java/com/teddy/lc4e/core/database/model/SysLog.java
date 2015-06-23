package com.teddy.lc4e.core.database.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class SysLog {
    @Id
    private ObjectId id;

    @DBRef
    private SysOperateType type;

    @DBRef
    private User user;

    private String description;

    private Date createTime;

    private Date updateTime;

    @PersistenceConstructor

    public SysLog(ObjectId id, SysOperateType type, User user, String description, Date createTime, Date updateTime) {
        this.id = id;
        this.type = type;
        this.user = user;
        this.description = description;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public SysOperateType getType() {
        return type;
    }

    public void setType(SysOperateType type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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