package com.teddy.lc4e.core.database.basemodel;

import com.teddy.lc4e.core.database.model.SysTACStatus;
import com.teddy.lc4e.core.entity.back.Attach;
import com.teddy.lc4e.core.entity.back.Attitude;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;
import java.util.List;

/**
 * Created by teddy on 2015/6/26.
 */
public abstract class TACBase extends BaseModel {

    @Indexed(unique = true)
    private String title;

    private String body;

    private List<Attach> attachs;

    @DBRef
    private SysTACStatus status;

    private List<Attitude> attitudes;

    public TACBase() {
    }

    public TACBase(String title, String body, List<Attach> attachs, SysTACStatus status, List<Attitude> attitudes) {
        this.title = title;
        this.body = body;
        this.attachs = attachs;
        this.status = status;
        this.attitudes = attitudes;
    }

    public TACBase(Date createTime, Date updateTime, String title, String body, List<Attach> attachs, SysTACStatus status, List<Attitude> attitudes) {
        super(createTime, updateTime);
        this.title = title;
        this.body = body;
        this.attachs = attachs;
        this.status = status;
        this.attitudes = attitudes;
    }
}
