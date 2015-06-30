package com.teddy.lc4e.core.database.model;

import com.teddy.lc4e.core.database.basemodel.BaseModel;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by teddy on 2015/6/23.
 */
@Document
public class SysAreaStatus extends BaseModel{

    @Id
    private ObjectId id;

    private String name;

    @Indexed(unique = true)
    private String abbr;

    private boolean visible;

    private boolean close;

    private boolean move;

    private boolean pubTopic;

    private boolean pubComment;

    @PersistenceConstructor

    public SysAreaStatus(Date createTime, Date updateTime, ObjectId id, String name, String abbr, boolean visible, boolean close, boolean move, boolean pubTopic, boolean pubComment) {
        super(createTime, updateTime);
        this.id = id;
        this.name = name;
        this.abbr = abbr;
        this.visible = visible;
        this.close = close;
        this.move = move;
        this.pubTopic = pubTopic;
        this.pubComment = pubComment;
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

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isClose() {
        return close;
    }

    public void setClose(boolean close) {
        this.close = close;
    }

    public boolean isMove() {
        return move;
    }

    public void setMove(boolean move) {
        this.move = move;
    }

    public boolean isPubTopic() {
        return pubTopic;
    }

    public void setPubTopic(boolean pubTopic) {
        this.pubTopic = pubTopic;
    }

    public boolean isPubComment() {
        return pubComment;
    }

    public void setPubComment(boolean pubComment) {
        this.pubComment = pubComment;
    }
}
