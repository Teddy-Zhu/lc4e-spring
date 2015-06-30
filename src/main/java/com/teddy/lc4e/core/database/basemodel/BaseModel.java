package com.teddy.lc4e.core.database.basemodel;

import java.util.Date;

/**
 * Created by teddy on 2015/6/26.
 */
public class BaseModel {

    private Date createTime;

    private Date updateTime;

    public BaseModel() {
    }

    public BaseModel(Date createTime, Date updateTime) {
        this.createTime = createTime;
        this.updateTime = updateTime;
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
