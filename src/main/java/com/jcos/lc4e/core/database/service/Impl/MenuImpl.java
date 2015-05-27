package com.jcos.lc4e.core.database.service.Impl;

import com.jcos.lc4e.core.database.dao.SysMenuMapper;
import com.jcos.lc4e.core.database.model.SysMenu;
import com.jcos.lc4e.core.database.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by teddy on 2015/5/20.
 */
@Service
public class MenuImpl implements MenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    /**
     *
     * @return
     */
    @Override
    public List<SysMenu> getSysMenus(){
        return sysMenuMapper.selectAllMenus();
    }
}
