package com.l1.service;

import java.util.List;
import java.util.Map;

import com.l1.entity.Supplier;

public interface SupplierService {
  public List<Supplier> find(Map<String, Object> map);

  /**
   * 获取总记录数
   * 
   * @param map
   * @return
   */
  public Long getTotal(Map<String, Object> map);

  public Integer add(Supplier supplier);

  public Integer update(Supplier supplier);

  public Integer delete(Integer id);



}
