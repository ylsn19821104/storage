package com.l1.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.l1.dao.PurchaseDtlDao;
import com.l1.entity.PurchaseDtl;
import com.l1.service.PurchaseDtlService;

@Service("purchaseDtlService")
public class PurchaseDtlServiceImpl implements PurchaseDtlService {
  @Resource
  private PurchaseDtlDao purchaseDtlDao;

  @Override
  public List<PurchaseDtl> find(Map<String, Object> map) {
    return purchaseDtlDao.find(map);
  }


  @Override
  public Long getTotalByPurchaseId(Integer id) {
    return purchaseDtlDao.getTotalByPurchaseId(id);
  }



  @Override
  public Integer add(PurchaseDtl purchaseDtl) {
    return purchaseDtlDao.add(purchaseDtl);
  }

  @Override
  public Integer update(PurchaseDtl purchaseDtl) {
    return purchaseDtlDao.update(purchaseDtl);
  }


  @Override
  public int batchSave(List<PurchaseDtl> dtls) {
    return purchaseDtlDao.batchSave(dtls);
  }


  @Override
  public List<PurchaseDtl> findByDtlIds(Integer[] dtlIds) {
    return purchaseDtlDao.findByDtlIds(dtlIds);
  }


  @Override
  public List<PurchaseDtl> findByPurchaseId(Integer id) {
    return purchaseDtlDao.findByPurchaseId(id);
  }


  @Override
  public PurchaseDtl findByDtlId(Integer dtlId) {
    return purchaseDtlDao.findByDtlId(dtlId);
  }


  @Override
  public Integer deleteByDtlId(Integer id) {
    return purchaseDtlDao.deleteByDtlId(id);
  }


  @Override
  public void deleteByDtlIds(Integer[] ids) {
    purchaseDtlDao.deleteByDtlIds(ids);
  }


  @Override
  public void deleteByPuchaseId(Integer purchaseId) {
    purchaseDtlDao.deleteByPuchaseId(purchaseId);
  }



}
