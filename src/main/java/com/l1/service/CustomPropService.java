package com.l1.service;

import com.l1.entity.CustomProp;

import java.util.List;
import java.util.Map;

/**
 * Created by luopotaotao on 2016/5/10.
 */
public interface CustomPropService {
    int getTotal(int id);

    Map<String,Object> findInfo(Integer id);

    List<CustomProp> find(int propId, Integer page, Integer rows);

    int save(CustomProp prop);

    public int batchSave(List<CustomProp> props);

    int remove(Integer[] ids);
}
