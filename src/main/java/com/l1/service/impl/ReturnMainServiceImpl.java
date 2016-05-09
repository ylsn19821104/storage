package com.l1.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.l1.dao.ReturnMainDao;
import com.l1.entity.ReturnMain;
import com.l1.service.ReturnMainService;

@Service("returnMainService")
public class ReturnMainServiceImpl implements ReturnMainService {
  @Resource
  private ReturnMainDao returnMainDao;

  @Override
  public List<ReturnMain> find(Map<String, Object> map) {
    return returnMainDao.find(map);
  }

  @Override
  public Long getTotal(Map<String, Object> map) {
    return returnMainDao.getTotal(map);
  }

  @Override
  public Integer add(ReturnMain returnMain) {
    return returnMainDao.add(returnMain);
  }

  @Override
  public Integer update(ReturnMain returnMain) {
    return returnMainDao.update(returnMain);
  }

  @Override
  public Integer deleteById(Integer id) {
    return returnMainDao.deleteById(id);
  }

  @Override
  public Integer delete(String[] ids) {
    return returnMainDao.delete(ids);
  }

  @Override
  public List<ReturnMain> findByIds(String ids) {
    return returnMainDao.findByIds(ids);
  }

  @Override
  public List<String> findNamesByIds(String ids) {
    return returnMainDao.findNamesByIds(ids);
  }

  @Override
  public ReturnMain findById(Integer id) {
    return returnMainDao.findById(id);
  }

  @Override
  public int save(ReturnMain returnMain) {
		return returnMainDao.save(returnMain);
  }

}
