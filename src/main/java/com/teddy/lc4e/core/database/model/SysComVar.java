package com.teddy.lc4e.core.database.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by teddy on 2015/6/14.
 */
@Document
public class SysComVar implements Serializable {

    @Transient
    private static final long serialVersionUID = 201606141153239L;

    @Id
    private ObjectId id;

    @Indexed(unique = true)
    private String strComConfigName;

    private String strComConfigValue;

    private Date createTime;

    public SysComVar() {
    }


    @PersistenceConstructor
    public SysComVar(ObjectId id, String strComConfigName, String strComConfigValue, Date createTime) {
        this.id = id;
        this.strComConfigName = strComConfigName;
        this.strComConfigValue = strComConfigValue;
        this.createTime = createTime;
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getStrComConfigName() {
        return strComConfigName;
    }

    public void setStrComConfigName(String strComConfigName) {
        this.strComConfigName = strComConfigName;
    }

    public String getStrComConfigValue() {
        return strComConfigValue;
    }

    public void setStrComConfigValue(String strComConfigValue) {
        this.strComConfigValue = strComConfigValue;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
