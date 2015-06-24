package com.teddy.lc4e.core.database.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class UserBasicInfo {
    @Id
    private ObjectId id;

    @DBRef
    @Indexed(unique = true)
    private User user;
    @Indexed(unique = true)
    private String phoneNumber;

    private String sign;

    private String avatar;

    @DBRef
    private SysAddress address;

    private Date birth;

    private Date createTime;

    private Date updateTime;

    public UserBasicInfo() {
    }

    @PersistenceConstructor

    public UserBasicInfo(ObjectId id, String phoneNumber, String sign, String avatar, SysAddress address, Date birth, Date createTime, Date updateTime) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.sign = sign;
        this.avatar = avatar;
        this.address = address;
        this.birth = birth;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public SysAddress getAddress() {
        return address;
    }

    public void setAddress(SysAddress address) {
        this.address = address;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}