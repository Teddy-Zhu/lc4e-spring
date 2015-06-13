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
public class MenuService {


    @Autowired
    private MenuRepository menuRepository;


    public List<SysMenu> getSysMenus(){
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(Sort.Direction.ASC,"intParentMenuId"));
        orders.add(new Order(Sort.Direction.ASC,"intMenuOrderId"));
        return menuRepository.findAll(new Sort(orders));
    }
}
