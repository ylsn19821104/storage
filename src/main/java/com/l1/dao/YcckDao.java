package com.l1.dao;

import com.l1.entity.Ycck;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface YcckDao {
  public List<Ycck> find(Map<String, Object> map);

  public List<Ycck> findByIds(String ids);

  public Ycck findById(Integer id);

  public List<String> findNamesByIds(String ids);

  public Long getTotal(Map<String, Object> map);

  public Integer add(Ycck color);

  public Integer update(Ycck color);

  public Integer delete(String[] ids);

  public Integer deleteById(int id);

  public void save(Ycck rent);

}
