package com.teddy.lc4e.core.database.service;

import com.teddy.lc4e.core.database.model.SysComVar;
import com.teddy.lc4e.core.database.repository.CommonConfigRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by teddy on 2015/6/14.
 */

@Service
public class ComVarService {

    @Autowired
    private CommonConfigRepository commonConfigRepository;

    public String getValueByComVarByName(String name){
        return commonConfigRepository.findByStrComConfigName(name).getStrComConfigValue();
    }

    public String getValueById(String id){
        return commonConfigRepository.findOne(new ObjectId(id)).getStrComConfigValue();
    }

    public List<SysComVar> getValuesByIds(String[] ids){
        return commonConfigRepository.findByStrComConfigNameIn(ids);
    }
}
