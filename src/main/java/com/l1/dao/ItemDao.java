package com.l1.dao;

import com.l1.entity.Item;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface ItemDao {
  public List<Item> find(Map<String, Object> map);

  public Long getTotal(Map<String, Object> map);

  public Integer add(Item item);

  public Integer update(Item item);

  public Integer delete(Integer id);

  public Item findById(Integer id);

}
