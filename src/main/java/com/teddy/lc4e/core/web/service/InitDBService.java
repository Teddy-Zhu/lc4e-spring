package com.teddy.lc4e.core.web.service;

import com.teddy.lc4e.core.database.model.SysComVar;
import com.teddy.lc4e.core.database.model.SysMenu;
import com.teddy.lc4e.core.database.repository.CommonConfigRepository;
import com.teddy.lc4e.core.database.repository.MenuRepository;
import com.teddy.lc4e.core.util.cache.CacheHandler;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by teddy on 2015/6/14.
 */
@Service
public class InitDBService {

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private CommonConfigRepository commonConfigRepository;

    @Autowired
    private CacheHandler cacheHandler;


    public boolean initdb() {
        //initMenu
        menuRepository.deleteAll();
        menuRepository.insert(new SysMenu(new ObjectId("557bc921aa0a9e955c54a701"), new ObjectId("557bc921aa0a9e955c54a701"), 0, "Menu", "Menu", "basic", "browser",new Date()));
        menuRepository.insert(new SysMenu(new ObjectId("557bc921aa0a9e955c54a702"), new ObjectId("557bc921aa0a9e955c54a701"), 1, "/", "Home", "basic", "home",new Date()));
        menuRepository.insert(new SysMenu(new ObjectId("557bc921aa0a9e955c54a703"), new ObjectId("557bc921aa0a9e955c54a701"), 2, "/", "Message", "basic", "mail",new Date()));
        menuRepository.insert(new SysMenu(new ObjectId("557bc921aa0a9e955c54a704"), new ObjectId("557bc921aa0a9e955c54a701"), 3, "/", "Friends", "basic", "user",new Date()));
        menuRepository.insert(new SysMenu(new ObjectId("557bc921aa0a9e955c54a705"), new ObjectId("557bc921aa0a9e955c54a701"), 4, "/", "Language", "basic", "font",new Date()));
        menuRepository.insert(new SysMenu(new ObjectId("557bc921aa0a9e955c54a706"), new ObjectId("557bc921aa0a9e955c54a705"), 1, "/", "C/C++", "basic", "",new Date()));
        menuRepository.insert(new SysMenu(new ObjectId("557bc921aa0a9e955c54a707"), new ObjectId("557bc921aa0a9e955c54a705"), 3, "/", "Java", "basic", "",new Date()));
        menuRepository.insert(new SysMenu(new ObjectId("557bc921aa0a9e955c54a708"), new ObjectId("557bc921aa0a9e955c54a705"), 2, "/", "Javascript", "basic", "",new Date()));
        menuRepository.insert(new SysMenu(new ObjectId("557bc921aa0a9e955c54a709"), new ObjectId("557bc921aa0a9e955c54a705"), 6, "/", "Script", "basic", "",new Date()));
        menuRepository.insert(new SysMenu(new ObjectId("557bc921aa0a9e955c54a70a"), new ObjectId("557bc921aa0a9e955c54a709"), 1, "/", "Python", "basic", "",new Date()));
        menuRepository.insert(new SysMenu(new ObjectId("557bc921aa0a9e955c54a70b"), new ObjectId("557bc921aa0a9e955c54a709"), 2, "/", "Ruby", "basic", "",new Date()));
        menuRepository.insert(new SysMenu(new ObjectId("557bc921aa0a9e955c54a70c"), new ObjectId("557bc921aa0a9e955c54a70a"), 5, "/", "Python1", "basic", "",new Date()));
        menuRepository.insert(new SysMenu(new ObjectId("557bc921aa0a9e955c54a70d"), new ObjectId("557bc921aa0a9e955c54a709"), 2, "/", "Python2", "basic", "",new Date()));
        menuRepository.insert(new SysMenu(new ObjectId("557bc921aa0a9e955c54a70e"), new ObjectId("557bc921aa0a9e955c54a70d"), 3, "/", "testtitle", "basic", "",new Date()));
        //clear cache
        cacheHandler.remove("data", "menus");


        //init Config
        commonConfigRepository.deleteAll();
        commonConfigRepository.insert(new SysComVar(null, "SiteName", "Light Community", new Date(),null));
        commonConfigRepository.insert(new SysComVar(null, "IndexPageSize", "20", new Date()));
        commonConfigRepository.insert(new SysComVar(null, "UseCache", "true", new Date()));

        //clear cache
        cacheHandler.clear("comVar");
        //use cache true
        cacheHandler.setCache("comVar", "UseCache", true);

        return true;
    }
}
