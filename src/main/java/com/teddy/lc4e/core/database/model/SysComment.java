package com.teddy.lc4e.core.database.model;

import com.teddy.lc4e.core.database.basemodel.TACBase;
import com.teddy.lc4e.core.entity.back.Attach;
import com.teddy.lc4e.core.entity.back.Attitude;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Document
@CompoundIndex(name = "sysComment_attitudes", def = "{'attitudes._id': 1}", unique = true)
public class SysComment extends TACBase {
    @Id
    private ObjectId id;

    @Indexed(unique = true)
    private Integer order;

    private ObjectId topic;

    @DBRef
    private User user;

    public SysComment() {
    }

    @PersistenceConstructor
    public SysComment(Date createTime, Date updateTime, String title, String body, List<Attach> attachs, SysTACStatus status, List<Attitude> attitudes, ObjectId id, Integer order, ObjectId topic, User user) {
        super(createTime, updateTime, title, body, attachs, status, attitudes);
        this.id = id;
        this.order = order;
        this.topic = topic;
        this.user = user;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public ObjectId getTopic() {
        return topic;
    }

    public void setTopic(ObjectId topic) {
        this.topic = topic;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}