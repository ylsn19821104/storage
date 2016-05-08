package com.l1.dao;

import com.l1.entity.Color;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface ColorDao {
  public List<Color> find(Map<String, Object> map);

  public List<Color> findByIds(String ids);

  public List<String> findNamesByIds(String[] ids);

  public Long getTotal(Map<String, Object> map);

  public Integer add(Color color);

  public Integer update(Color color);

  public Integer delete(Integer id);
  
  public Color findById(Integer id);

}
