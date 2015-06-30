package com.teddy.lc4e.core.entity.back;

/**
 * Created by teddy on 2015/6/28.
 */
public class Attach {

    private String name;

    private String location;

    private boolean needUser;

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isNeedUser() {
        return needUser;
    }

    public void setNeedUser(boolean needUser) {
        this.needUser = needUser;
    }
}
