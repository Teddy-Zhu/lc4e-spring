package com.teddy.lc4e.core.web.service;

import com.teddy.lc4e.core.database.model.User;
import com.teddy.lc4e.core.database.model.UserBasicInfo;
import com.teddy.lc4e.core.database.service.UserBasicDao;
import com.teddy.lc4e.core.database.service.UserDao;
import com.teddy.lc4e.core.util.common.Global;
import com.teddy.lc4e.plugins.shiro.credentials.PassDisposer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    private PassDisposer passDisposer;

    @Autowired
    private ComVariableData comVariableData;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserBasicDao userBasicDao;


    private boolean useCache;

    public boolean createUser(User user, UserBasicInfo basic) {
        Object sr = comVariableData.getComVarByName(Global.SREG);
        if (sr == null) {
            sr = false;
        }

        if ((boolean) sr) {
            passDisposer.encryptPassword(user);
            user.setId(null);
            user.setLocked(false);
            userDao.insertUser(user);
        } else {
            passDisposer.encryptPassword(basic.getUser());
            basic.getUser().setLocked(false);
            basic.getUser().setId(null);
            userBasicDao.insertBasicUser(basic);
        }


        return true;
    }
}
