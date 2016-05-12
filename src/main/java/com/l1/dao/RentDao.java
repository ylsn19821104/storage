package com.l1.dao;

import com.l1.entity.Rent;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface RentDao {
  public List<Rent> find(Map<String, Object> map);

  public List<Rent> findByIds(String ids);

  public Rent findById(Integer id);

  public List<String> findNamesByIds(String ids);

  public Long getTotal(Map<String, Object> map);

  public Integer add(Rent color);

  public Integer update(Rent color);

  public Integer delete(String[] ids);

  public Integer deleteById(int id);

  public int save(Rent rent);

  int finish(Integer[] ids);
}
