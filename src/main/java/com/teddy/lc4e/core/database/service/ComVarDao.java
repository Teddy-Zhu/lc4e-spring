package com.teddy.lc4e.core.database.service;

import com.teddy.lc4e.core.database.model.SysComVar;
import com.teddy.lc4e.core.database.repository.CommonConfigRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by teddy on 2015/6/14.
 */

@Service
public class ComVarDao {

    @Autowired
    private CommonConfigRepository commonConfigRepository;

    public Object getValueByName(String name) {
        SysComVar sysComVar = commonConfigRepository.findByName(name);
        if (sysComVar == null) {
            return "";
        } else {
            return sysComVar.getValue();
        }
    }

    public Object getValueById(String id) {
        return commonConfigRepository.findOne(new ObjectId(id)).getValue();
    }

    public List<SysComVar> getValuesByIds(String[] ids) {
        ObjectId[] list = new ObjectId[ids.length];
        for (int i = 0, len = ids.length; i < len; i++) {
            list[i] = new ObjectId(ids[i]);
        }
        return commonConfigRepository.findByIdIn(list);
    }

    public List<SysComVar> getSysComVarsByNames(String[] names) {
        return commonConfigRepository.findByNameIn(names);
    }

    public Object[] getValuesByNames(String[] names) {
        List<SysComVar> list = getSysComVarByNames(names);
        Object[] values = new String[names.length];
        for (int i = 0, len = names.length; i < len; i++) {
            values[i] = list.get(i).getValue();
        }
        return values;
    }

    public Map<String, SysComVar> getSysComVarMapByNames(String[] names) {
        List<SysComVar> list = getSysComVarByNames(names);
        Map<String, SysComVar> values = new HashMap<String, SysComVar>();
        for (int i = 0, len = names.length; i < len; i++) {
            values.put(list.get(i).getName(), list.get(i));
        }
        return values;
    }

    public Map<String, Object> getValuesMapByNames(String[] names) {
        List<SysComVar> list = getSysComVarByNames(names);
        Map<String, Object> values = new HashMap<String, Object>();
        for (int i = 0, len = names.length; i < len; i++) {
            values.put(list.get(i).getName(), list.get(i).getValue());
        }
        return values;
    }


    public SysComVar getSysComVarByName(String name) {
        return commonConfigRepository.findByName(name);
    }

    private List<SysComVar> getSysComVarByNames(String[] names) {
        return commonConfigRepository.findByNameIn(names);
    }
}
