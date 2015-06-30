package com.teddy.lc4e.core.database.model;

import com.teddy.lc4e.core.database.basemodel.BaseModel;
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
public class SysComVar extends BaseModel implements Serializable {

    @Transient
    private static final long serialVersionUID = 201606141153239L;

    @Id
    private ObjectId id;

    @Indexed(unique = true)
    private String name;

    private Object value;

    private String error;

    private String description;

    public SysComVar() {
    }
    @PersistenceConstructor
    public SysComVar(ObjectId id, String name, Object value, String error, String description,Date createTime, Date updateTime) {
        super(createTime, updateTime);
        this.id = id;
        this.name = name;
        this.value = value;
        this.error = error;
        this.description = description;
    }


    public SysComVar(ObjectId id, String name, Object value, Date createTime) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.setCreateTime(createTime);
        this.description = "";
        this.error = "";
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
