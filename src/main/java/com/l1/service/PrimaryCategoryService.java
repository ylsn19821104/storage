package com.l1.service;

import java.util.List;
import java.util.Map;

import com.l1.entity.PrimaryCategory;

public interface PrimaryCategoryService {
  public List<PrimaryCategory> find(Map<String, Object> map);

  public Long getTotal(Map<String, Object> map);

  public Integer add(PrimaryCategory primary);

  public Integer update(PrimaryCategory primary);

  public Integer delete(Integer id);

  public PrimaryCategory findById(Integer id);

}
