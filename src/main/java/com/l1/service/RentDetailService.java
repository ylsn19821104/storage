package com.l1.service;

import java.util.List;
import java.util.Map;

import com.l1.entity.RentDetail;

public interface RentDetailService {
  public List<RentDetail> find(Map<String, Object> map);

  public List<RentDetail> findByIds(String ids);

  public RentDetail findById(Integer id);

  public List<String> findNamesByIds(String ids);

  public Long getTotal(Map<String, Object> map);

  public Integer add(RentDetail Rent);

  public Integer update(RentDetail Rent);

  public Integer delete(Integer id);

  public Integer remove(String[] ids);

  public List<RentDetail> findAllByRentId(int rentId);
}
