package com.l1.service;

import java.util.List;
import java.util.Map;

import com.l1.entity.Ycck;

public interface YcckService {
  public List<Ycck> find(Map<String, Object> map);

  public List<Ycck> findByIds(String ids);

  public Ycck findById(Integer id);

  public List<String> findNamesByIds(String ids);

  public Long getTotal(Map<String, Object> map);

  public Integer add(Ycck Ycck);

  public Integer update(Ycck Ycck);

  public Integer deleteById(Integer id);

  public void save(Ycck rent);

  public Integer delete(String[] ids);
}
