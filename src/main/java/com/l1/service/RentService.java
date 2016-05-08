package com.l1.service;

import java.util.List;
import java.util.Map;

import com.l1.entity.Rent;

public interface RentService {
  public List<Rent> find(Map<String, Object> map);

  public List<Rent> findByIds(String ids);

  public Rent findById(Integer id);

  public List<String> findNamesByIds(String ids);

  public Long getTotal(Map<String, Object> map);

  public Integer add(Rent Rent);

  public Integer update(Rent Rent);

  public Integer deleteById(Integer id);

  public int save(Rent rent);

  public Integer delete(String[] ids);
}
