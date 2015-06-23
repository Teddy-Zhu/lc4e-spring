package com.teddy.lc4e.core.database.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class UserBalance {
    @Id
    private ObjectId id;
    @DBRef
    @Indexed(unique = true)
    private User user;

    private Long balances;

    private Date updateTime;

    public UserBalance() {
    }

    @PersistenceConstructor
    public UserBalance(ObjectId id, Long balances, Date updateTime) {
        this.id = id;
        this.balances = balances;
        this.updateTime = updateTime;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Long getBalances() {
        return balances;
    }

    public void setBalances(Long balances) {
        this.balances = balances;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}