package com.l1.dao;

import com.l1.entity.MinorCategory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface MinorCategoryDao {
  public List<MinorCategory> find(Map<String, Object> map);

  public Long getTotal(Map<String, Object> map);

  public Integer add(MinorCategory minor);

  public Integer update(MinorCategory minor);

  public Integer delete(Integer id);

  public MinorCategory findById(Integer minorId);

}
