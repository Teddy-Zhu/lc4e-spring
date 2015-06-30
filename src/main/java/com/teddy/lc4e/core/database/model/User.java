package com.teddy.lc4e.core.database.model;

import com.teddy.lc4e.core.database.basemodel.BaseModel;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class User extends BaseModel {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    private String name;
    @Indexed(unique = true)
    private String mail;
    @Indexed(unique = true)
    private String nick;

    private String password;

    private String passSalt;

    private boolean locked;

    public User() {
    }

    public User(String name, String password) {
        this.id = null;
        this.name = name;
        this.mail = "";
        this.nick = "";
        this.password = password;
        this.passSalt = "";
        this.locked = true;
    }

    @PersistenceConstructor
    public User(Date createTime, Date updateTime, ObjectId id, String name, String mail, String nick, String password, String passSalt, boolean locked) {
        super(createTime, updateTime);
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.nick = nick;
        this.password = password;
        this.passSalt = passSalt;
        this.locked = locked;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassSalt() {
        return passSalt;
    }

    public void setPassSalt(String passSalt) {
        this.passSalt = passSalt;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
}