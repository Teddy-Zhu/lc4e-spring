package com.teddy.lc4e.core.database.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    private String userName;
    @Indexed(unique = true)
    private String userMail;
    @Indexed(unique = true)
    private String userNick;

    private String userPass;

    private String userPassSalt;

    private boolean locked;

    public User() {
    }

    public User(String userName, String userPass) {
        super();
        this.id = null;
        this.userName = userName;
        this.userMail = "";
        this.userNick = "";
        this.userPass = userPass;
        this.userPassSalt = "";
        this.locked = false;
    }

    @PersistenceConstructor

    public User(ObjectId id, String userName, String userMail, String userNick, String userPass, String userPassSalt, boolean locked) {
        this.id = id;
        this.userName = userName;
        this.userMail = userMail;
        this.userNick = userNick;
        this.userPass = userPass;
        this.userPassSalt = userPassSalt;
        this.locked = locked;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserPassSalt() {
        return userPassSalt;
    }

    public void setUserPassSalt(String userPassSalt) {
        this.userPassSalt = userPassSalt;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
}