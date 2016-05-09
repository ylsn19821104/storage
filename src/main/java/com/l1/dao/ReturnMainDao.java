package com.l1.dao;

import com.l1.entity.ReturnMain;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface ReturnMainDao {
  public List<ReturnMain> find(Map<String, Object> map);

  public List<ReturnMain> findByIds(String ids);

  public ReturnMain findById(Integer id);

  public List<String> findNamesByIds(String ids);

  public Long getTotal(Map<String, Object> map);

  public Integer add(ReturnMain color);

  public Integer update(ReturnMain color);

  public Integer delete(String[] ids);

  public Integer deleteById(int id);

  public int save(ReturnMain rent);

}
