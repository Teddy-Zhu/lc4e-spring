package com.teddy.lc4e.core.database.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class SysTACStatus {
    @Id
    private ObjectId id;

    private String name;
    @Indexed(unique = true)
    private String abbr;

    private boolean visible;

    private boolean editable;

    private boolean lock;

    private boolean release;

    private boolean delete;

    private boolean move;

    private Date createTime;

    private Date updateTime;

    @PersistenceConstructor

    public SysTACStatus(ObjectId id, String name, String abbr, boolean visible, boolean editable, boolean lock, boolean release, boolean delete, boolean move, Date createTime, Date updateTime) {
        this.id = id;
        this.name = name;
        this.abbr = abbr;
        this.visible = visible;
        this.editable = editable;
        this.lock = lock;
        this.release = release;
        this.delete = delete;
        this.move = move;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public boolean isLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public boolean isRelease() {
        return release;
    }

    public void setRelease(boolean release) {
        this.release = release;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public boolean isMove() {
        return move;
    }

    public void setMove(boolean move) {
        this.move = move;
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
}