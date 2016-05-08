package com.l1.dao;

import com.l1.entity.RentDtl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface RentDtlDao {
  public List<RentDtl> find(Map<String, Object> map);

  public List<RentDtl> findByIds(String ids);

  public RentDtl findById(Integer id);

  public List<String> findNamesByIds(String ids);

  public Long getTotal(Map<String, Object> map);

  public Integer add(RentDtl color);

  public Integer update(RentDtl color);

  public Integer deleteById(Integer id);

  public void delete(String[] ids);

}
