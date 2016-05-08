package com.l1.dao;

import com.l1.entity.PurchaseDtl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface PurchaseDtlDao {
  public List<PurchaseDtl> find(Map<String, Object> map);

  public List<PurchaseDtl> findByIds(String ids);

  public PurchaseDtl findById(Integer id);

  public List<String> findNamesByIds(String ids);

  public Long getTotal(Map<String, Object> map);

  public Integer add(PurchaseDtl color);

  public Integer update(PurchaseDtl color);

  public Integer deleteById(Integer id);

  public void delete(String[] ids);

}
