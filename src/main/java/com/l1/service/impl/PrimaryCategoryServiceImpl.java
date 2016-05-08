package com.l1.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.l1.dao.PrimaryCategoryDao;
import com.l1.entity.PrimaryCategory;
import com.l1.service.PrimaryCategoryService;

@Service("primaryCategoryService")
public class PrimaryCategoryServiceImpl implements PrimaryCategoryService {
  @Resource
  private PrimaryCategoryDao primaryDao;

  @Override
  public List<PrimaryCategory> find(Map<String, Object> map) {
    return primaryDao.find(map);
  }

  @Override
  public Long getTotal(Map<String, Object> map) {
    return primaryDao.getTotal(map);
  }

  @Override
  public Integer add(PrimaryCategory primary) {
    return primaryDao.add(primary);
  }

  @Override
  public Integer update(PrimaryCategory primary) {
    return primaryDao.update(primary);
  }

  @Override
  public Integer delete(Integer id) {
    return primaryDao.delete(id);
  }

  @Override
  public PrimaryCategory findById(Integer id) {
    return primaryDao.findById(id);
  }

}
