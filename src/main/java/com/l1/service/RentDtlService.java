package com.l1.service;

import java.util.List;
import java.util.Map;

import com.l1.entity.RentDtl;

public interface RentDtlService {
  public List<RentDtl> find(Map<String, Object> map);

  public List<RentDtl> findByIds(String ids);

  public RentDtl findById(Integer id);

  public List<String> findNamesByIds(String ids);

  public Long getTotal(Map<String, Object> map);

  public Integer add(RentDtl Rent);

  public Integer update(RentDtl Rent);

  public Integer deleteById(Integer id);

  public void delete(Integer[] ids);
}
