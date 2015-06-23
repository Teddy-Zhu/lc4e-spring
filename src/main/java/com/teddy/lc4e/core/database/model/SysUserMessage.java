package com.teddy.lc4e.core.database.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class SysUserMessage {
    @Id
    private ObjectId id;

    private ObjectId user;

    private ObjectId destUser;

    private boolean read;

    private String title;

    private String body;

    private Date createTime;

    private Date updateTime;

    @PersistenceConstructor
    public SysUserMessage(ObjectId id, ObjectId user, ObjectId destUser, boolean read, String title, String body, Date createTime, Date updateTime) {
        this.id = id;
        this.user = user;
        this.destUser = destUser;
        this.read = read;
        this.title = title;
        this.body = body;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getUser() {
        return user;
    }

    public void setUser(ObjectId user) {
        this.user = user;
    }

    public ObjectId getDestUser() {
        return destUser;
    }

    public void setDestUser(ObjectId destUser) {
        this.destUser = destUser;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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