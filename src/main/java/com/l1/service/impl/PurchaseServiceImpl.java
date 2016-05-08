package com.l1.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.l1.dao.PurchaseDao;
import com.l1.entity.Purchase;
import com.l1.service.PurchaseService;

@Service("purchaseService")
public class PurchaseServiceImpl implements PurchaseService {
  @Resource
  private PurchaseDao purchaseDao;

  @Override
  public List<Purchase> find(Map<String, Object> map) {
    return purchaseDao.find(map);
  }

  @Override
  public Long getTotal(Map<String, Object> map) {
    return purchaseDao.getTotal(map);
  }

  @Override
  public Integer add(Purchase purchase) {
    return purchaseDao.add(purchase);
  }

  @Override
  public Integer update(Purchase purchase) {
    return purchaseDao.update(purchase);
  }

  @Override
  public Integer deleteById(Integer id) {
    return purchaseDao.deleteById(id);
  }

  @Override
  public Integer delete(String[] ids) {
    return purchaseDao.delete(ids);
  }

  @Override
  public List<Purchase> findByIds(String ids) {
    return purchaseDao.findByIds(ids);
  }

  @Override
  public List<String> findNamesByIds(String ids) {
    return purchaseDao.findNamesByIds(ids);
  }

  @Override
  public Purchase findById(Integer id) {
    return purchaseDao.findById(id);
  }

  @Override
  public void save(Purchase purchase) {
    purchaseDao.save(purchase);
  }

}
