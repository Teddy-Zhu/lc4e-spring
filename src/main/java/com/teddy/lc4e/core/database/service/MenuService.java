package com.teddy.lc4e.core.database.service;


import com.teddy.lc4e.core.database.model.SysMenu;
import com.teddy.lc4e.core.database.model.SysRole;
import com.teddy.lc4e.core.database.repository.MenuRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by teddy on 2015/6/12.
 */
@Service
public class MenuService {


    @Autowired
    private MenuRepository menuRepository;


    public List<SysMenu> getSysMenus() {
        List<Order> orders = new ArrayList<Order>();
        orders.add(new Order(Sort.Direction.ASC, "intParentMenuId"));
        orders.add(new Order(Sort.Direction.ASC, "intMenuOrderId"));
        return menuRepository.findAll(new Sort(orders));
    }

    public boolean initdb(){
        menuRepository.deleteAll();
        menuRepository.insert(new SysMenu(new ObjectId("557bc921aa0a9e955c54a701"),new ObjectId("557bc921aa0a9e955c54a701"),1,"Menu","Menu","basic","browser"));
        menuRepository.insert(new SysMenu(new ObjectId("557bc921aa0a9e955c54a702"),new ObjectId("557bc921aa0a9e955c54a701"),1,"/","Home","basic","home"));
        menuRepository.insert(new SysMenu(new ObjectId("557bc921aa0a9e955c54a703"),new ObjectId("557bc921aa0a9e955c54a701"),2,"/","Message","basic","mail"));
        menuRepository.insert(new SysMenu(new ObjectId("557bc921aa0a9e955c54a704"),new ObjectId("557bc921aa0a9e955c54a701"),3,"/","Friends","basic","user"));
        menuRepository.insert(new SysMenu(new ObjectId("557bc921aa0a9e955c54a705"),new ObjectId("557bc921aa0a9e955c54a701"),4,"/","Language","basic","font"));
        menuRepository.insert(new SysMenu(new ObjectId("557bc921aa0a9e955c54a706"),new ObjectId("557bca0caa0a9e955c54a705"),1,"/","C/C++","basic",""));
        menuRepository.insert(new SysMenu(new ObjectId("557bc921aa0a9e955c54a707"),new ObjectId("557bca0caa0a9e955c54a705"),3,"/","Java","basic",""));
        menuRepository.insert(new SysMenu(new ObjectId("557bc921aa0a9e955c54a708"),new ObjectId("557bca0caa0a9e955c54a705"),2,"/","Javascript","basic",""));
        menuRepository.insert(new SysMenu(new ObjectId("557bc921aa0a9e955c54a709"),new ObjectId("557bca0caa0a9e955c54a705"),6,"/","Script","basic",""));
        menuRepository.insert(new SysMenu(new ObjectId("557bc921aa0a9e955c54a70a"),new ObjectId("557bca70aa0a9e955c54a709"),1,"/","Python","basic",""));
        menuRepository.insert(new SysMenu(new ObjectId("557bc921aa0a9e955c54a70b"),new ObjectId("557bca70aa0a9e955c54a709"),2,"/","Ruby","basic",""));
        menuRepository.insert(new SysMenu(new ObjectId("557bc921aa0a9e955c54a70c"),new ObjectId("557bcaa9aa0a9e955c54a70a"),5,"/","Python1","basic",""));
        menuRepository.insert(new SysMenu(new ObjectId("557bc921aa0a9e955c54a70d"),new ObjectId("557bca70aa0a9e955c54a709"),2,"/","Python2","basic",""));
        menuRepository.insert(new SysMenu(new ObjectId("557bc921aa0a9e955c54a70e"),new ObjectId("557bcad5aa0a9e955c54a70d"),3,"/","testtitle","basic",""));

        return true;
    }
}
