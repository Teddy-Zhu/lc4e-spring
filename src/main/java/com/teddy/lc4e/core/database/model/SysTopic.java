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

import java.util.*;

@Document
@CompoundIndex(name = "sysComment_attitudes", def = "{'attitudes._id': 1}", unique = true)
public class SysTopic extends TACBase{
    @Id
    private ObjectId id;

    @DBRef
    private SysArea area;

    @DBRef
    private UserBasicInfo user;

    private Set<String> tags;

    private Integer otherCount;

    private Set<ObjectId> viewedUsers;

    public SysTopic() {
    }

    @PersistenceConstructor
    public SysTopic(Date createTime, Date updateTime, String title, String body, List<Attach> attachs, SysTACStatus status, List<Attitude> attitudes, ObjectId id, SysArea area, UserBasicInfo user, Set<String> tags, Integer otherCount, Set<ObjectId> viewedUsers) {
        super(createTime, updateTime, title, body, attachs, status, attitudes);
        this.id = id;
        this.area = area;
        this.user = user;
        this.tags = tags;
        this.otherCount = otherCount;
        this.viewedUsers = viewedUsers;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public SysArea getArea() {
        return area;
    }

    public void setArea(SysArea area) {
        this.area = area;
    }

    public UserBasicInfo getUser() {
        return user;
    }

    public void setUser(UserBasicInfo user) {
        this.user = user;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public Integer getOtherCount() {
        return otherCount;
    }

    public void setOtherCount(Integer otherCount) {
        this.otherCount = otherCount;
    }

    public Set<ObjectId> getViewedUsers() {
        return viewedUsers;
    }

    public void setViewedUsers(Set<ObjectId> viewedUsers) {
        this.viewedUsers = viewedUsers;
    }
}