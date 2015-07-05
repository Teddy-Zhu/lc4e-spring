package com.teddy.lc4e.core.database.service;


import com.teddy.lc4e.core.database.model.SysMenu;
import com.teddy.lc4e.core.database.repository.MenuRepository;
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
public class MenuDao {


    @Autowired
    private MenuRepository menuRepository;


    public List<SysMenu> getSysMenus() {
        List<Order> orders = new ArrayList<Order>();
        orders.add(new Order(Sort.Direction.ASC, "parentId"));
        orders.add(new Order(Sort.Direction.ASC, "order"));
        return menuRepository.findAll(new Sort(orders));
    }

}
