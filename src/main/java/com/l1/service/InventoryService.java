package com.l1.service;

import java.util.List;
import java.util.Map;

import com.l1.entity.Inventory;

public interface InventoryService {
  public List<Inventory> find(Map<String, Object> map);

  public List<Inventory> findByIds(String[] ids);

  public Inventory findById(Integer id);

  public Long getTotal(Map<String, Object> map);

  public Integer add(Inventory inventory);

  public Integer update(Inventory inventory);

  public Integer deleteById(Integer id);

  public void save(Inventory inventory);

  public Integer delete(String[] ids);
}
