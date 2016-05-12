package com.l1.service;

import java.util.List;
import java.util.Map;

import com.l1.entity.ReturnMain;

public interface ReturnMainService {
	public List<ReturnMain> find(Map<String, Object> map);

	public List<ReturnMain> findByIds(String ids);

	public ReturnMain findById(Integer id);

	public List<String> findNamesByIds(String ids);

	public Long getTotal(Map<String, Object> map);

	public Integer add(ReturnMain ReturnMain);

	public Integer update(ReturnMain ReturnMain);

	public Integer deleteById(Integer id);

	public int save(ReturnMain rent);

	public Integer delete(String[] ids);
}
