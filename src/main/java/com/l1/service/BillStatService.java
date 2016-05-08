package com.l1.service;

import java.util.List;
import java.util.Map;

import com.l1.entity.BillStat;

public interface BillStatService {
	public List<BillStat> find(Map<String, Object> map);

	public BillStat findById(Integer id);

	public List<BillStat> findByIds(String ids);

	public List<String> findNamesByIds(String ids);

	public Long getTotal(Map<String, Object> map);

	public Integer add(BillStat BillStat);

	public Integer update(BillStat BillStat);

	public Integer delete(Integer id);
}
