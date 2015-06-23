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
public class SysAreaCollect {
    @Id
    private ObjectId id;

    @Indexed(unique = true)
    private ObjectId user;

    private List<ObjectId> collectedAreas;

    private Date createTime;

    private Date updateTime;

    @PersistenceConstructor

    public SysAreaCollect(ObjectId id, ObjectId user, List<ObjectId> collectedAreas, Date createTime, Date updateTime) {
        this.id = id;
        this.user = user;
        this.collectedAreas = collectedAreas;
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

    public List<ObjectId> getCollectedAreas() {
        return collectedAreas;
    }

    public void setCollectedAreas(List<ObjectId> collectedAreas) {
        this.collectedAreas = collectedAreas;
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