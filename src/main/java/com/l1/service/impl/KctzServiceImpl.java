package com.l1.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.l1.dao.KctzDao;
import com.l1.entity.Kctz;
import com.l1.service.KctzService;

@Service("kctzService")
public class KctzServiceImpl implements KctzService {
  @Resource
  private KctzDao kctzDao;

  @Override
  public List<Kctz> find(Map<String, Object> map) {
    return kctzDao.find(map);
  }

  @Override
  public Long getTotal(Map<String, Object> map) {
    return kctzDao.getTotal(map);
  }

  @Override
  public Integer add(Kctz kctz) {
    return kctzDao.add(kctz);
  }

  @Override
  public Integer update(Kctz kctz) {
    return kctzDao.update(kctz);
  }

  @Override
  public Integer deleteById(Integer id) {
    return kctzDao.deleteById(id);
  }

  @Override
  public Integer delete(String[] ids) {
    return kctzDao.delete(ids);
  }

  @Override
  public List<Kctz> findByIds(String ids) {
    return kctzDao.findByIds(ids);
  }

  @Override
  public List<String> findNamesByIds(String ids) {
    return kctzDao.findNamesByIds(ids);
  }

  @Override
  public Kctz findById(Integer id) {
    return kctzDao.findById(id);
  }

  @Override
  public void save(Kctz kctz) {
    kctzDao.save(kctz);
  }

}
