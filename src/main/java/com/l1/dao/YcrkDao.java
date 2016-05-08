package com.l1.dao;

import com.l1.entity.Ycrk;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface YcrkDao {
  public List<Ycrk> find(Map<String, Object> map);

  public List<Ycrk> findByIds(String ids);

  public Ycrk findById(Integer id);

  public List<String> findNamesByIds(String ids);

  public Long getTotal(Map<String, Object> map);

  public Integer add(Ycrk color);

  public Integer update(Ycrk color);

  public Integer delete(String[] ids);

  public Integer deleteById(int id);

  public void save(Ycrk rent);

}
