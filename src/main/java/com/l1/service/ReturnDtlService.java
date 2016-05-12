package com.l1.service;

import java.util.List;
import java.util.Map;

import com.l1.entity.ReturnDtl;

public interface ReturnDtlService {
  public List<ReturnDtl> find(Map<String, Object> map);

  public List<ReturnDtl> findByIds(String ids);

  public ReturnDtl findById(Integer id);

  public List<String> findNamesByIds(String ids);

  public Long getTotal(Map<String, Object> map);

  public Integer add(ReturnDtl Rent);

  public Integer update(ReturnDtl Rent);

  public Integer deleteById(Integer id);

  public void delete(String[] ids);
}
