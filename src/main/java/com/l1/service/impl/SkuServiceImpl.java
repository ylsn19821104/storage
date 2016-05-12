package com.l1.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.l1.entity.Image;
import org.springframework.stereotype.Service;

import com.l1.dao.SkuDao;
import com.l1.entity.Sku;
import com.l1.service.SkuService;

@Service("skuService")
public class SkuServiceImpl implements SkuService {
  @Resource
  SkuDao skuDao;

  @Override
  public List<Sku> find(Map<String, Object> map) {
    return skuDao.find(map);
  }

    @Override
    public List<Map<String, String>> findForCombo(Map<String, Object> map) {
        return skuDao.findForCombo(map);
    }

    @Override
  public List<Sku> findByIds(String ids) {
    return skuDao.findByIds(ids);
  }

  @Override
  public Sku findById(Integer id) {
    return skuDao.findById(id);
  }

  @Override
  public Long getTotal(Map<String, Object> map) {
    return skuDao.getTotal(map);
  }

  @Override
  public Integer add(Sku sku) {
    return skuDao.add(sku);
  }

  @Override
  public Integer update(Sku sku) {
    return skuDao.update(sku);
  }


  @Override
  public Integer deleteById(Integer id) {
    return skuDao.deleteById(id);
  }

  @Override
  public Integer deleteByItemId(Integer itemId) {
    return skuDao.deleteByItemId(itemId);
  }

}
