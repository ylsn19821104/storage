package com.l1.dao;

import com.l1.entity.Dic;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by luopotaotao on 2016/5/8.
 */
@Repository
public interface DicDao {
    List<Dic> query(String key);
}
