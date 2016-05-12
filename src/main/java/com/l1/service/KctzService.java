package com.l1.service;

import java.util.List;
import java.util.Map;

import com.l1.entity.Kctz;

public interface KctzService {
  public List<Kctz> find(Map<String, Object> map);

  public List<Kctz> findByIds(String ids);

  public Kctz findById(Integer id);

  public List<String> findNamesByIds(String ids);

  public Long getTotal(Map<String, Object> map);

  public Integer add(Kctz Kctz);

  public Integer update(Kctz Kctz);

  public Integer deleteById(Integer id);

  public void save(Kctz rent);

  public Integer delete(String[] ids);
}
