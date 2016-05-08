package com.l1.service;

import com.l1.entity.Dic;

import java.util.List;
import java.util.Map;

/**
 * Created by luopotaotao on 2016/5/8.
 */
public interface DicService {
    List<Dic> query(String key);
}
