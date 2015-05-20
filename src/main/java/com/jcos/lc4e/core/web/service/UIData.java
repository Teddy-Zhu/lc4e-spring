package com.jcos.lc4e.core.web.service;

import com.jcos.lc4e.core.database.model.SysMenu;
import com.jcos.lc4e.core.database.service.MenuService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by teddy on 2015/5/20.
 */
@Service
public class UIData {

    private static final Logger LOGGER = Logger.getLogger(UIData.class);
    @Autowired
    private MenuService menuService;

    public SysMenu getMenuTree(){
        List<SysMenu> allMenus = menuService.getSysMenus();
        SysMenu menuTree = new SysMenu();
        if(allMenus==null || allMenus.isEmpty()){
            return menuTree;
        }
        menuTree = allMenus.get(0);
        getMenu(allMenus,menuTree);
        return menuTree;
    }

    private void getMenu(List<SysMenu> allMenus,SysMenu curMenu){
        for (int i = 0,len=allMenus.size(); i <len ;) {
            if (allMenus.get(i).getIntparentmenuid() == curMenu.getIntmenuid()){
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
