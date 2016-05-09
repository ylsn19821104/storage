package com.l1.dao;

import org.springframework.stereotype.Repository;

/**
 * Created by luopotaotao on 2016/5/8.
 */
@Repository
public interface SeqDao {
    int add(String prefix);
    int find(String prefix);
}
