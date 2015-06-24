package com.teddy.lc4e.core.database.service;

import com.teddy.lc4e.core.database.model.UserBasicInfo;
import com.teddy.lc4e.core.database.repository.UserBasicRepository;
import com.teddy.lc4e.core.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by teddy on 2015/6/24.
 */
@Service
public class UserBasicDao {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserBasicRepository userBasicRepository;

    public boolean insertBasicUser(UserBasicInfo basic) {
        userRepository.insert(basic.getUser());
        userBasicRepository.insert(basic);
        return true;
    }
}
