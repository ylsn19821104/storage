package com.l1.service;

import java.util.List;
import java.util.Map;

import com.l1.entity.Purchase;
import com.l1.entity.PurchaseDtl;

public interface PurchaseService {
  public List<Purchase> find(Map<String, Object> map);

  public List<Purchase> findByIds(String ids);

  public Purchase findById(Integer id);

  public List<String> findNamesByIds(String ids);

  public Long getTotal(Map<String, Object> map);

  public Integer add(Purchase Purchase);

  public Integer update(Purchase Purchase);

  public Integer deleteById(Integer id);

  public int save(Purchase purchase );

  public Integer delete(Integer[] ids);
  
  public int savePurchaseWithDetails(Purchase purchase,List<PurchaseDtl> dtls);
  public int updatePurchaseWithDetails(Purchase purchase,List<PurchaseDtl> dtls);

  public int finish(Integer[] ids, Integer[] warehouseIds);

  int unfinish(Integer[] ids, Integer[] warehouseIds);
  
}
