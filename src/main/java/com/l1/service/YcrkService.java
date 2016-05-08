package com.l1.service;

import java.util.List;
import java.util.Map;

import com.l1.entity.Ycrk;

public interface YcrkService {
  public List<Ycrk> find(Map<String, Object> map);

  public List<Ycrk> findByIds(String ids);

  public Ycrk findById(Integer id);

  public List<String> findNamesByIds(String ids);

  public Long getTotal(Map<String, Object> map);

  public Integer add(Ycrk Ycrk);

  public Integer update(Ycrk Ycrk);

  public Integer deleteById(Integer id);

  public void save(Ycrk rent);

  public Integer delete(String[] ids);
}
