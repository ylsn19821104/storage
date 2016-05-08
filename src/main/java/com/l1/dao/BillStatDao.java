package com.l1.dao;

import com.l1.entity.BillStat;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface BillStatDao {
	public List<BillStat> find(Map<String, Object> map);

	public BillStat findById(Integer id);

	public List<BillStat> findByIds(String ids);

	public List<String> findNamesByIds(String ids);

	public Long getTotal(Map<String, Object> map);

	public Integer add(BillStat color);

	public Integer update(BillStat color);

	public Integer delete(Integer id);

}
