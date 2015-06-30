package com.teddy.lc4e.core.database.model;

import com.teddy.lc4e.core.database.basemodel.BaseModel;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Set;

/**
 * Created by teddy on 2015/6/25.
 */
@Document
public class Dbtest extends BaseModel {
    @Id
    private ObjectId id;

    @Indexed(unique = true)
    private String name;

    private String value;

    private String t;

    private Set<String> tags;

    public Dbtest() {
    }

    public Dbtest(ObjectId id, String name, String value, String t, Set<String> tags) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.t = t;
        this.tags = tags;
    }

    @PersistenceConstructor
    public Dbtest(Date createTime, Date updateTime, ObjectId id, String name, String value, String t, Set<String> tags) {
        super(createTime, updateTime);
        this.id = id;
        this.name = name;
        this.value = value;
        this.t = t;
        this.tags = tags;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }
}
