package com.l1.service;

import java.util.List;
import java.util.Map;

import com.l1.entity.Color;

public interface ColorService {
  public List<Color> find(Map<String, Object> map);

  public List<Color> findByIds(String ids);

  public List<String> findNamesByIds(String[] ids);

  public Long getTotal(Map<String, Object> map);

  public Integer add(Color Color);

  public Integer update(Color Color);

  public Integer delete(Integer id);
  
  public Color findById(Integer id);
}
