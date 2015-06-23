package com.teddy.lc4e.core.database.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

@Document
public class SysTopicView {
    @Id
    private ObjectId id;

    @Indexed(unique = true)
    private ObjectId topic;

    private Set<ObjectId> viewedUsers;

    private Integer otherCount;

    @PersistenceConstructor

    public SysTopicView(ObjectId id, ObjectId topic, Set<ObjectId> viewedUsers, Integer otherCount) {
        this.id = id;
        this.topic = topic;
        this.viewedUsers = viewedUsers;
        this.otherCount = otherCount;
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

    public Set<ObjectId> getViewedUsers() {
        return viewedUsers;
    }

    public void setViewedUsers(Set<ObjectId> viewedUsers) {
        this.viewedUsers = viewedUsers;
    }

    public Integer getOtherCount() {
        return otherCount;
    }

    public void setOtherCount(Integer otherCount) {
        this.otherCount = otherCount;
    }
}