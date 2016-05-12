package com.l1.dao;

import com.l1.entity.Purchase;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface PurchaseDao {
  public List<Purchase> find(Map<String, Object> map);

  public List<Purchase> findByIds(String ids);

  public Purchase findById(Integer id);

  public List<String> findNamesByIds(String ids);

  public Long getTotal(Map<String, Object> map);

  public Integer add(Purchase purchase);

  public Integer update(Purchase purchase);

  public Integer delete(Integer[] ids);

  public Integer deleteById(int id);

  public int save(Purchase rent);
  
  int finish(Integer[] ids);
  
  int unfinish(Integer[] ids);

}
