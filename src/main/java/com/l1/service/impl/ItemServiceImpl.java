package com.l1.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.l1.dao.ItemDao;
import com.l1.entity.Item;
import com.l1.service.ItemService;

@Service("itemService")
public class ItemServiceImpl implements ItemService {

  @Resource
  private ItemDao itemDao;

  @Override
  public List<Item> find(Map<String, Object> map) {
    return itemDao.find(map);
  }

  @Override
  public Long getTotal(Map<String, Object> map) {
    return itemDao.getTotal(map);
  }

  @Override
  public Integer add(Item item) {
    return itemDao.add(item);
  }

  @Override
  public Integer update(Item item) {
    return itemDao.update(item);
  }

  @Override
  public Integer delete(Integer id) {
    return itemDao.delete(id);
  }

  @Override
  public Item findById(Integer id) {
    return itemDao.findById(id);
  }

}
