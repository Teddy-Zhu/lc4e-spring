package com.teddy.lc4e.core.util.exception;

import com.teddy.lc4e.core.database.service.UserDao;
import com.teddy.lc4e.core.util.realm.UserRealm;

/**
 * Created by teddy on 2015/6/13.
 */
public class ShiroRealmServiceBridge {
    public static void postInject(UserRealm realm, UserDao service) {
        realm.setService(service);
    }
}
