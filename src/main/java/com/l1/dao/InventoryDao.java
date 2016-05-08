package com.l1.dao;

import com.l1.entity.Inventory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface InventoryDao {
  public List<Inventory> find(Map<String, Object> map);

  public List<Inventory> findByIds(String[] ids);

  public Inventory findById(Integer id);

  public List<String> findNamesByIds(String ids);

  public Long getTotal(Map<String, Object> map);

  public Integer add(Inventory inventory);

  public Integer update(Inventory inventory);

  public Integer delete(String[] ids);

  public Integer deleteById(int id);

  public void save(Inventory inventory);

}
