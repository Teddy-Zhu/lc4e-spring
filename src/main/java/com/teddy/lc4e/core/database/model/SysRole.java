package com.teddy.lc4e.core.database.model;

import com.teddy.lc4e.core.database.basemodel.BaseModel;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by teddy on 2015/6/12.
 */
@Document
public class SysRole extends BaseModel{
    @Id
    private ObjectId id;

    @Indexed(unique = true)
    private String abbr;

    private String name;

    private String description;

    private boolean available;

    public SysRole() {
    }

    @PersistenceConstructor


    public SysRole(ObjectId id, String abbr, String name, String description, boolean available, Date createTime, Date updateTime) {
        super(createTime,updateTime);
        this.id = id;
        this.abbr = abbr;
        this.name = name;
        this.description = description;
        this.available = available;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

}
