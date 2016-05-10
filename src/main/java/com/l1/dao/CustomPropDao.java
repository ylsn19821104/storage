package com.l1.dao;

import com.l1.entity.CustomProp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by luopotaotao on 2016/5/10.
 */
@Repository
public interface CustomPropDao {
    int getTotal(int id);

    Map<String,Object> findInfo(Integer id);

    List<CustomProp> find(Integer propId,Integer page, Integer rows);

    int update(CustomProp prop);

    int batchSave(List<CustomProp> props);

    int remove(Integer[] ids);

    int add(CustomProp prop);
}
