package com.teddy.lc4e.core.database.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Set;

@Document
public class SysTopicCollected {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    private ObjectId user;

    private Set<ObjectId> collectedTopics;

    private Date createTime;

    @PersistenceConstructor

    public SysTopicCollected(ObjectId id, ObjectId user, Set<ObjectId> collectedTopics, Date createTime) {
        this.id = id;
        this.user = user;
        this.collectedTopics = collectedTopics;
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

    public Set<ObjectId> getCollectedTopics() {
        return collectedTopics;
    }

    public void setCollectedTopics(Set<ObjectId> collectedTopics) {
        this.collectedTopics = collectedTopics;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}