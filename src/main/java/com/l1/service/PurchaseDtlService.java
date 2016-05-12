package com.l1.service;

import java.util.List;
import java.util.Map;

import com.l1.entity.PurchaseDtl;

public interface PurchaseDtlService {
  public Integer add(PurchaseDtl color);

  public int batchSave(List<PurchaseDtl> dtls);

  public Integer update(PurchaseDtl color);

  public List<PurchaseDtl> find(Map<String, Object> map);

  public List<PurchaseDtl> findByDtlIds(Integer[] dtlIds);

  public List<PurchaseDtl> findByPurchaseId(Integer id);

  public PurchaseDtl findByDtlId(Integer dtlId);

  public Long getTotalByPurchaseId(Integer id);

  public Integer deleteByDtlId(Integer id);

  public void deleteByDtlIds(Integer[] ids);


  public void deleteByPuchaseId(Integer purchaseId);
}
