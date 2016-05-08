package com.l1.dao;

import com.l1.entity.KctzDtl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface KctzDtlDao {
  public List<KctzDtl> find(Map<String, Object> map);

  public List<KctzDtl> findByIds(String ids);

  public KctzDtl findById(Integer id);

  public List<String> findNamesByIds(String ids);

  public Long getTotal(Map<String, Object> map);

  public Integer add(KctzDtl color);

  public Integer update(KctzDtl color);

  public Integer deleteById(Integer id);

  public void delete(String[] ids);

}
