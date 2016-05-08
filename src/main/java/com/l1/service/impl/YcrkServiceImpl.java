package com.l1.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.l1.dao.YcrkDao;
import com.l1.entity.Ycrk;
import com.l1.service.YcrkService;

@Service("ycrkService")
public class YcrkServiceImpl implements YcrkService {
  @Resource
  private YcrkDao ycrkDao;

  @Override
  public List<Ycrk> find(Map<String, Object> map) {
    return ycrkDao.find(map);
  }

  @Override
  public Long getTotal(Map<String, Object> map) {
    return ycrkDao.getTotal(map);
  }

  @Override
  public Integer add(Ycrk ycrk) {
    return ycrkDao.add(ycrk);
  }

  @Override
  public Integer update(Ycrk ycrk) {
    return ycrkDao.update(ycrk);
  }

  @Override
  public Integer deleteById(Integer id) {
    return ycrkDao.deleteById(id);
  }

  @Override
  public Integer delete(String[] ids) {
    return ycrkDao.delete(ids);
  }

  @Override
  public List<Ycrk> findByIds(String ids) {
    return ycrkDao.findByIds(ids);
  }

  @Override
  public List<String> findNamesByIds(String ids) {
    return ycrkDao.findNamesByIds(ids);
  }

  @Override
  public Ycrk findById(Integer id) {
    return ycrkDao.findById(id);
  }

  @Override
  public void save(Ycrk ycrk) {
    ycrkDao.save(ycrk);
  }

}
