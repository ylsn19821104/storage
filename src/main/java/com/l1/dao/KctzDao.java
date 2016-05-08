package com.l1.dao;

import com.l1.entity.Kctz;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface KctzDao {
  public List<Kctz> find(Map<String, Object> map);

  public List<Kctz> findByIds(String ids);

  public Kctz findById(Integer id);

  public List<String> findNamesByIds(String ids);

  public Long getTotal(Map<String, Object> map);

  public Integer add(Kctz purchase);

  public Integer update(Kctz purchase);

  public Integer delete(String[] ids);

  public Integer deleteById(int id);

  public void save(Kctz rent);

}
