package com.l1.dao;

import com.l1.entity.PrimaryCategory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface PrimaryCategoryDao {
  public List<PrimaryCategory> find(Map<String, Object> map);

  public Long getTotal(Map<String, Object> map);

  public Integer add(PrimaryCategory primary);

  public Integer update(PrimaryCategory primary);

  public Integer delete(Integer id);

  public PrimaryCategory findById(Integer id);

}
