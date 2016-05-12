package com.l1.service;

import java.util.List;
import java.util.Map;

import com.l1.entity.Item;

public interface ItemService {
  public List<Item> find(Map<String, Object> map);

  public Long getTotal(Map<String, Object> map);

  public Integer add(Item item);

  public Integer update(Item item);

  public Integer delete(Integer id);

  public Item findById(Integer id);

}
