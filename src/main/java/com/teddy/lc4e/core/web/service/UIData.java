package com.teddy.lc4e.core.web.service;

import com.teddy.lc4e.core.database.model.SysMenu;
import com.teddy.lc4e.core.database.service.MenuDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by teddy on 2015/5/20.
 */
@Service
public class UIData {
    private static final Logger LOGGER = Logger.getLogger(UIData.class);
    @Autowired
    private MenuDao menuDao;

    public List<SysMenu> getMenuTree(){

        List<SysMenu> allMenus = menuDao.getSysMenus();
        SysMenu menuTree = new SysMenu();
        if(allMenus==null || allMenus.isEmpty()){
            return new ArrayList<SysMenu>();
        }
        menuTree = allMenus.get(0);
        allMenus.remove(0);
        getMenu(allMenus,menuTree);
        return menuTree.getChildMenus();
    }

    private void getMenu(List<SysMenu> allMenus,SysMenu curMenu){
        for (int i = 0,len=allMenus.size(); i <len ;) {
            if (allMenus.get(i).getParentId().toString().equals(curMenu.getId().toString())){
                curMenu.getChildMenus().add(allMenus.get(i));
                allMenus.remove(i);
                len--;
            }else{
                i++;
            }
        }
        for (int i = 0,len=curMenu.getChildMenus().size(); i <len ; i++) {
            getMenu(allMenus,curMenu.getChildMenus().get(i));
        }
    }
}