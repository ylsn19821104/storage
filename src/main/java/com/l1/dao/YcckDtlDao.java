package com.l1.dao;

import com.l1.entity.YcckDtl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface YcckDtlDao {
  public List<YcckDtl> find(Map<String, Object> map);

  public List<YcckDtl> findByIds(String ids);

  public YcckDtl findById(Integer id);

  public List<String> findNamesByIds(String ids);

  public Long getTotal(Map<String, Object> map);

  public Integer add(YcckDtl color);

  public Integer update(YcckDtl color);

  public Integer deleteById(Integer id);

  public void delete(String[] ids);

}
