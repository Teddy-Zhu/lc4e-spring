package com.teddy.lc4e.core.entity.back;

import org.bson.types.ObjectId;

/**
 * Created by teddy on 2015/6/28.
 */
public class Attitude {

    private ObjectId id;
    private boolean attitude;

    public Attitude(ObjectId id, boolean attitude) {
        this.id = id;
        this.attitude = attitude;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public boolean isAttitude() {
        return attitude;
    }

    public void setAttitude(boolean attitude) {
        this.attitude = attitude;
    }
}
