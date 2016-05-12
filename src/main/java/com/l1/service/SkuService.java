package com.l1.service;

import java.util.List;
import java.util.Map;

import com.l1.entity.Sku;

public interface SkuService {


  public List<Sku> find(Map<String, Object> map);

 public List<Map<String,String>> findForCombo(Map<String, Object> map);

  public List<Sku> findByIds(String ids);

  public Sku findById(Integer id);

  public Long getTotal(Map<String, Object> map);

  public Integer add(Sku sku);

  public Integer update(Sku sku);

  public Integer deleteById(Integer id);

  public Integer deleteByItemId(Integer itemId);



}
