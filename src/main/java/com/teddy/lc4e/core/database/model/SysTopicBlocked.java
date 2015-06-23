package com.teddy.lc4e.core.database.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Set;

@Document
public class SysTopicBlocked {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    private ObjectId user;

    private Set<ObjectId> blockedTopics;

    private Date createTime;

    @PersistenceConstructor

    public SysTopicBlocked(ObjectId id, ObjectId user, Set<ObjectId> blockedTopics, Date createTime) {
        this.id = id;
        this.user = user;
        this.blockedTopics = blockedTopics;
        this.createTime = createTime;
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

    public Set<ObjectId> getBlockedTopics() {
        return blockedTopics;
    }

    public void setBlockedTopics(Set<ObjectId> blockedTopics) {
        this.blockedTopics = blockedTopics;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}