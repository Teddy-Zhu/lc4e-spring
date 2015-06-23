package com.teddy.lc4e.core.util.mongodb;

import com.mongodb.DBObject;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by teddy on 2015/6/20.
 */
@Component
public class BeforeSaveHandler extends AbstractMongoEventListener {

    @Override
    public void onBeforeSave(Object source, DBObject dbo) {
        Field field = null;
        Method method = null;
        Class objClass = source.getClass();
        try {
            field = objClass.getDeclaredField("updateTime");
        } catch (NoSuchFieldException e) {
            field = null;
        }

        if (field != null) {
            dbo.put("updateTime", new Date());
        }
    }
}
