package com.l1.service.impl;

import com.l1.dao.SeqDao;
import com.l1.service.SeqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by luopotaotao on 2016/5/9.
 */
@Service
public class SeqServiceImpl implements SeqService {

    @Autowired
    private SeqDao seqDao;

    @Transactional(isolation = Isolation.SERIALIZABLE,propagation = Propagation.REQUIRES_NEW)
    @Override
    public int next(String prefix) {
        seqDao.add(prefix);
        return seqDao.find(prefix);
    }
}
