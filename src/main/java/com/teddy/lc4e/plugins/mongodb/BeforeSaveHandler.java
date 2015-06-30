package com.teddy.lc4e.plugins.mongodb;

import com.mongodb.DBObject;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * Created by teddy on 2015/6/20.
 */
@Component
public class BeforeSaveHandler extends AbstractMongoEventListener {

    @Override
    public void onBeforeSave(Object source, DBObject dbo) {
        Field field = null;
        Class objClass = source.getClass();

        if (findField(objClass, "updateTime")) {
            dbo.put("updateTime", new Date());
        }
    }

    private boolean findField(Class clz, String fieldName) {
        Field field = null;
        try {
            field = clz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            clz = clz.getSuperclass();
            if (clz != Object.class && findField(clz, fieldName)) {
                return true;
            }
        }
        return field != null;
    }

}
