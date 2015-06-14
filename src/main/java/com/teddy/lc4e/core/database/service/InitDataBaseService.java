package com.teddy.lc4e.core.database.service;

import com.teddy.lc4e.core.database.model.SysComVar;
import com.teddy.lc4e.core.database.model.SysMenu;
import com.teddy.lc4e.core.database.repository.CommonConfigRepository;
import com.teddy.lc4e.core.database.repository.MenuRepository;
import com.teddy.lc4e.core.web.service.WebCacheManager;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by teddy on 2015/6/14.
 */
@Service
public class InitDataBaseService {

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private CommonConfigRepository commonConfigRepository;

    @Autowired
    private WebCacheManager webCacheManager;


    public boolean initdb() {
        //initMenu
        menuRepository.deleteAll();
        menuRepository.insert(new SysMenu(new ObjectId("557bc921aa0a9e955c54a701"), new ObjectId("557bc921aa0a9e955c54a701"), 0, "Menu", "Menu", "basic", "browser"));
        menuRepository.insert(new SysMenu(new ObjectId("557bc921aa0a9e955c54a702"), new ObjectId("557bc921aa0a9e955c54a701"), 1, "/", "Home", "basic", "home"));
        menuRepository.insert(new SysMenu(new ObjectId("557bc921aa0a9e955c54a703"), new ObjectId("557bc921aa0a9e955c54a701"), 2, "/", "Message", "basic", "mail"));
        menuRepository.insert(new SysMenu(new ObjectId("557bc921aa0a9e955c54a704"), new ObjectId("557bc921aa0a9e955c54a701"), 3, "/", "Friends", "basic", "user"));
        menuRepository.insert(new SysMenu(new ObjectId("557bc921aa0a9e955c54a705"), new ObjectId("557bc921aa0a9e955c54a701"), 4, "/", "Language", "basic", "font"));
        menuRepository.insert(new SysMenu(new ObjectId("557bc921aa0a9e955c54a706"), new ObjectId("557bc921aa0a9e955c54a705"), 1, "/", "C/C++", "basic", ""));
        menuRepository.insert(new SysMenu(new ObjectId("557bc921aa0a9e955c54a707"), new ObjectId("557bc921aa0a9e955c54a705"), 3, "/", "Java", "basic", ""));
        menuRepository.insert(new SysMenu(new ObjectId("557bc921aa0a9e955c54a708"), new ObjectId("557bc921aa0a9e955c54a705"), 2, "/", "Javascript", "basic", ""));
        menuRepository.insert(new SysMenu(new ObjectId("557bc921aa0a9e955c54a709"), new ObjectId("557bc921aa0a9e955c54a705"), 6, "/", "Script", "basic", ""));
        menuRepository.insert(new SysMenu(new ObjectId("557bc921aa0a9e955c54a70a"), new ObjectId("557bc921aa0a9e955c54a709"), 1, "/", "Python", "basic", ""));
        menuRepository.insert(new SysMenu(new ObjectId("557bc921aa0a9e955c54a70b"), new ObjectId("557bc921aa0a9e955c54a709"), 2, "/", "Ruby", "basic", ""));
        menuRepository.insert(new SysMenu(new ObjectId("557bc921aa0a9e955c54a70c"), new ObjectId("557bc921aa0a9e955c54a70a"), 5, "/", "Python1", "basic", ""));
        menuRepository.insert(new SysMenu(new ObjectId("557bc921aa0a9e955c54a70d"), new ObjectId("557bc921aa0a9e955c54a709"), 2, "/", "Python2", "basic", ""));
        menuRepository.insert(new SysMenu(new ObjectId("557bc921aa0a9e955c54a70e"), new ObjectId("557bc921aa0a9e955c54a70d"), 3, "/", "testtitle", "basic", ""));
        //clear cache
        webCacheManager.clearCacheByCacheNameAndKey("data", "menus");


        //init Config
        commonConfigRepository.deleteAll();
        commonConfigRepository.insert(new SysComVar(null,"SiteName","Light Community",new Date()));
        //clear cache
        webCacheManager.clearCacheByCacheName("comVar");

        return true;
    }
}