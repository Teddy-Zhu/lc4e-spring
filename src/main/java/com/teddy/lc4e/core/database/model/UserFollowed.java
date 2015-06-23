package com.teddy.lc4e.core.database.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Document
public class UserFollowed {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    private ObjectId user;

    private Set<ObjectId> followedUsers;

    private Date createTime;

    @PersistenceConstructor

    public UserFollowed(ObjectId id, ObjectId user, Set<ObjectId> followedUsers, Date createTime) {
        this.id = id;
        this.user = user;
        this.followedUsers = followedUsers;
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

    public Set<ObjectId> getFollowedUsers() {
        return followedUsers;
    }

    public void setFollowedUsers(Set<ObjectId> followedUsers) {
        this.followedUsers = followedUsers;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}