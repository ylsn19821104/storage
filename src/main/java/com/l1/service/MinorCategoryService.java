package com.l1.service;

import java.util.List;
import java.util.Map;

import com.l1.entity.MinorCategory;

public interface MinorCategoryService {
  public List<MinorCategory> find(Map<String, Object> map);

  public Long getTotal(Map<String, Object> map);

  public Integer add(MinorCategory minor);

  public Integer update(MinorCategory minor);

  public Integer delete(Integer id);

  public MinorCategory findById(Integer minorId);

}
