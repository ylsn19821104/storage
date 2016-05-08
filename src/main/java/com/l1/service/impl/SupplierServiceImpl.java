package com.l1.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.l1.dao.SupplierDao;
import com.l1.entity.Supplier;
import com.l1.service.SupplierService;

@Service("supplierService")
public class SupplierServiceImpl implements SupplierService {
  @Resource
  private SupplierDao supplierDao;

  @Override
  public List<Supplier> find(Map<String, Object> map) {
    return supplierDao.find(map);
  }

  @Override
  public Long getTotal(Map<String, Object> map) {
    return supplierDao.getTotal(map);
  }

  @Override
  public Integer add(Supplier supplier) {
    return supplierDao.add(supplier);
  }

  @Override
  public Integer update(Supplier supplier) {
    return supplierDao.update(supplier);
  }

  @Override
  public Integer delete(Integer id) {
    return supplierDao.delete(id);
  }

}
