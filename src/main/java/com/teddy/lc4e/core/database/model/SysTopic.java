package com.teddy.lc4e.core.database.model;

import com.teddy.lc4e.core.entity.Status.TopicStatus;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class SysTopic {
    @Id
    private ObjectId id;

    @DBRef
    private SysArea area;

    @DBRef
    private UserBasicInfo user;

    @Indexed(unique = true)
    private String title;

    private String body;

    @DBRef
    private SysTACStatus status;

    private Date createTime;

    private Date updateTime;

    public SysTopic() {
    }

    @PersistenceConstructor

    public SysTopic(ObjectId id, SysArea area, UserBasicInfo user, String title, String body, SysTACStatus status, Date createTime, Date updateTime) {
        this.id = id;
        this.area = area;
        this.user = user;
        this.title = title;
        this.body = body;
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

    public SysArea getArea() {
        return area;
    }

    public void setArea(SysArea area) {
        this.area = area;
    }

    public UserBasicInfo getUser() {
        return user;
    }

    public void setUser(UserBasicInfo user) {
        this.user = user;
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