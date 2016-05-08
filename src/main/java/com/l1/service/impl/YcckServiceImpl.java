package com.l1.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.l1.dao.YcckDao;
import com.l1.entity.Ycck;
import com.l1.service.YcckService;

@Service("ycckService")
public class YcckServiceImpl implements YcckService {
  @Resource
  private YcckDao ycckDao;

  @Override
  public List<Ycck> find(Map<String, Object> map) {
    return ycckDao.find(map);
  }

  @Override
  public Long getTotal(Map<String, Object> map) {
    return ycckDao.getTotal(map);
  }

  @Override
  public Integer add(Ycck ycck) {
    return ycckDao.add(ycck);
  }

  @Override
  public Integer update(Ycck ycck) {
    return ycckDao.update(ycck);
  }

  @Override
  public Integer deleteById(Integer id) {
    return ycckDao.deleteById(id);
  }

  @Override
  public Integer delete(String[] ids) {
    return ycckDao.delete(ids);
  }

  @Override
  public List<Ycck> findByIds(String ids) {
    return ycckDao.findByIds(ids);
  }

  @Override
  public List<String> findNamesByIds(String ids) {
    return ycckDao.findNamesByIds(ids);
  }

  @Override
  public Ycck findById(Integer id) {
    return ycckDao.findById(id);
  }

  @Override
  public void save(Ycck ycck) {
    ycckDao.save(ycck);
  }

}
