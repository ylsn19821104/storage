package com.l1.service.impl;

import com.l1.dao.SeqDao;
import com.l1.service.SeqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by luopotaotao on 2016/5/9.
 */
@Service
public class SeqServiceImpl implements SeqService {

    @Autowired
    private SeqDao seqDao;

    private static SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

    @Transactional(isolation = Isolation.SERIALIZABLE,propagation = Propagation.REQUIRES_NEW)
    @Override
    public String next(String prefix) {
        seqDao.add(prefix);
        int number = seqDao.find(prefix);
        StringBuilder sb = new StringBuilder();
        String str1 = format.format(new Date());
        String str2 = String.format("%1$,06d", number);
        return sb.append(prefix).append(str1).append(str2).toString();
    }
}
