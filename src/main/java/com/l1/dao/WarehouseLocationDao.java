package com.l1.dao;

import com.l1.entity.WarehouseLocation;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface WarehouseLocationDao {
	public List<WarehouseLocation> find(Map<String, Object> map);

	public List<WarehouseLocation> findByIds(String ids);

	public WarehouseLocation findById(Integer id);

	public List<String> findNamesByIds(String ids);

	public Long getTotal(Map<String, Object> map);

	public Integer add(WarehouseLocation warehouseLocation);

	public Integer update(WarehouseLocation warehouseLocation);

	public Integer delete(Integer id);

}
