package com.l1.dao;

import com.l1.entity.YcrkDtl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface YcrkDtlDao {
  public List<YcrkDtl> find(Map<String, Object> map);

  public List<YcrkDtl> findByIds(String ids);

  public YcrkDtl findById(Integer id);

  public List<String> findNamesByIds(String ids);

  public Long getTotal(Map<String, Object> map);

  public Integer add(YcrkDtl color);

  public Integer update(YcrkDtl color);

  public Integer deleteById(Integer id);

  public void delete(String[] ids);

}
