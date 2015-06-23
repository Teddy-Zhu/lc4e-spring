package com.teddy.lc4e.core.database.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
public class SysCommentLike {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    private ObjectId comment;

    private List<ObjectId> likedUsers;

    private Date createTime;

    @PersistenceConstructor

    public SysCommentLike(ObjectId id, ObjectId comment, List<ObjectId> likedUsers, Date createTime) {
        this.id = id;
        this.comment = comment;
        this.likedUsers = likedUsers;
        this.createTime = createTime;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getComment() {
        return comment;
    }

    public void setComment(ObjectId comment) {
        this.comment = comment;
    }

    public List<ObjectId> getLikedUsers() {
        return likedUsers;
    }

    public void setLikedUsers(List<ObjectId> likedUsers) {
        this.likedUsers = likedUsers;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}