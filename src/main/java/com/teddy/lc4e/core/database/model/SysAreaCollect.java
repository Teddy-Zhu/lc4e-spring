package com.teddy.lc4e.core.database.model;

import com.teddy.lc4e.core.database.basemodel.BaseModel;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
public class SysAreaCollect extends BaseModel{
    @Id
    private ObjectId id;

    @Indexed(unique = true)
    private ObjectId user;

    private List<ObjectId> collectedAreas;

    @PersistenceConstructor

    public SysAreaCollect(Date createTime, Date updateTime, ObjectId id, ObjectId user, List<ObjectId> collectedAreas) {
        super(createTime, updateTime);
        this.id = id;
        this.user = user;
        this.collectedAreas = collectedAreas;
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
}