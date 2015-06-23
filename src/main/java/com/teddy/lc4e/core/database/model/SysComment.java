package com.teddy.lc4e.core.database.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class SysComment {
    @Id
    private ObjectId id;

    private ObjectId topic;

    private String title;

    private String body;

    @DBRef
    private UserBasicInfo user;

    @DBRef
    private SysTACStatus status;

    private Date createTime;

    private Date updateTime;

    public SysComment() {
    }

    @PersistenceConstructor

    public SysComment(ObjectId id, ObjectId topic, String title, String body, UserBasicInfo user, SysTACStatus status, Date createTime, Date updateTime) {
        this.id = id;
        this.topic = topic;
        this.title = title;
        this.body = body;
        this.user = user;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getTopic() {
        return topic;
    }

    public void setTopic(ObjectId topic) {
        this.topic = topic;
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

    public UserBasicInfo getUser() {
        return user;
    }

    public void setUser(UserBasicInfo user) {
        this.user = user;
    }

    public SysTACStatus getStatus() {
        return status;
    }

    public void setStatus(SysTACStatus status) {
        this.status = status;
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