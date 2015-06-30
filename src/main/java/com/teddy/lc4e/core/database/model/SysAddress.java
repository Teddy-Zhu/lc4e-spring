package com.teddy.lc4e.core.database.model;

import com.teddy.lc4e.core.database.basemodel.BaseModel;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by teddy on 2015/6/19.
 */
@Document
public class SysAddress extends BaseModel {

    @Id
    private ObjectId id;
    @Indexed(unique = true)
    private String province;

    private String city;

    private String region;

    private String street;

    @PersistenceConstructor
    public SysAddress(Date createTime, Date updateTime, ObjectId id, String province, String city, String region, String street) {
        super(createTime, updateTime);
        this.id = id;
        this.province = province;
        this.city = city;
        this.region = region;
        this.street = street;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

}
