package com.teddy.lc4e.core.database.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Set;

@Document
public class SysTopicLike {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    private ObjectId topic;

    private Set<ObjectId> likedUsers;

    private Date createTime;

    @PersistenceConstructor

    public SysTopicLike(ObjectId id, ObjectId topic, Set<ObjectId> likedUsers, Date createTime) {
        this.id = id;
        this.topic = topic;
        this.likedUsers = likedUsers;
        this.createTime = createTime;
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

    public Set<ObjectId> getLikedUsers() {
        return likedUsers;
    }

    public void setLikedUsers(Set<ObjectId> likedUsers) {
        this.likedUsers = likedUsers;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}