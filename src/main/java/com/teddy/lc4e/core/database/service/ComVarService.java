package com.teddy.lc4e.core.database.service;

import com.teddy.lc4e.core.database.model.SysComVar;
import com.teddy.lc4e.core.database.repository.CommonConfigRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created by teddy on 2015/6/14.
 */

@Service
public class ComVarService {

    @Autowired
    private CommonConfigRepository commonConfigRepository;

    public String getValueByComVarByName(String name){
        SysComVar sysComVar =commonConfigRepository.findByName(name);
        if (sysComVar == null){
            return "";
        }else{
            return sysComVar.getValue();
        }
    }

    public String getValueById(String id){
        return commonConfigRepository.findOne(new ObjectId(id)).getValue();
    }

    public List<SysComVar> getValuesByIds(String[] ids){
        return commonConfigRepository.findByNameIn(ids);
    }

}
