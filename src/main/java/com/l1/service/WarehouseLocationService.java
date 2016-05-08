package com.l1.service;

import java.util.List;
import java.util.Map;

import com.l1.entity.WarehouseLocation;

public interface WarehouseLocationService {
	public List<WarehouseLocation> find(Map<String, Object> map);

	public List<WarehouseLocation> findByIds(String ids);

	public WarehouseLocation findById(Integer id);

	public List<String> findNamesByIds(String ids);

	public Long getTotal(Map<String, Object> map);

	public Integer add(WarehouseLocation Size);

	public Integer update(WarehouseLocation Size);

	public Integer delete(Integer id);
}
