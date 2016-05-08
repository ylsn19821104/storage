package com.l1.dao;

import com.l1.entity.Supplier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface SupplierDao {
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

  public Integer delete(Integer supplier);

}
