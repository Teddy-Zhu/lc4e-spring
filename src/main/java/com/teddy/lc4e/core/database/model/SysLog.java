package com.teddy.lc4e.core.database.model;

import com.teddy.lc4e.core.database.basemodel.BaseModel;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class SysLog extends BaseModel{
    @Id
    private ObjectId id;

    @DBRef
    private SysOperateType type;

    @DBRef
    private User user;

    private String description;

    @PersistenceConstructor

    public SysLog(ObjectId id, SysOperateType type, User user, String description, Date createTime, Date updateTime) {
        super(createTime, updateTime);
        this.id = id;
        this.type = type;
        this.user = user;
        this.description = description;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public SysOperateType getType() {
        return type;
    }

    public void setType(SysOperateType type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}