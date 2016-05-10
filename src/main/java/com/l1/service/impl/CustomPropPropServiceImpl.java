package com.l1.service.impl;

import com.l1.dao.CustomPropDao;
import com.l1.entity.CustomProp;
import com.l1.service.CustomPropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by luopotaotao on 2016/5/10.
 */
@Repository("customPropService")
public class CustomPropPropServiceImpl implements CustomPropService {
    @Autowired
    private CustomPropDao customPropDao;
    @Override
    public int getTotal(int id) {
        return customPropDao.getTotal(id);
    }

    @Override
    public Map<String, Object> findInfo(Integer id) {
        return customPropDao.findInfo(id);
    }

    @Override
    public List<CustomProp> find(int propId, Integer page, Integer rows) {
        return customPropDao.find(propId,page,rows);
    }

    @Override
    public int save(CustomProp prop) {
        return prop.getId()!=null?customPropDao.update(prop):customPropDao.add(prop);
    }
    @Override
    public int batchSave(List<CustomProp> props) {
        return customPropDao.batchSave(props);
    }

    @Override
    public int remove(Integer[] ids) {
        return customPropDao.remove(ids);
    }
}
