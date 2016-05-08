package com.l1.service.impl;

import com.l1.dao.DicDao;
import com.l1.entity.Dic;
import com.l1.service.DicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by luopotaotao on 2016/5/8.
 */
@Service("dicService")
public class DicServiceImpl implements DicService {
    @Autowired
    private DicDao dicDao;
    @Override
    public List<Dic> query(String key) {
        return dicDao.query(key);
    }
}
