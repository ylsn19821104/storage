package com.l1.service;

import java.util.List;
import java.util.Map;

import com.l1.entity.Warehouse;

public interface WarehouseService {
	public List<Warehouse> find(Map<String, Object> map);

    public List<Map<String,String>> findForCombo(Map<String, Object> map);

	public Long getTotal(Map<String, Object> map);

	public Integer add(Warehouse Warehouse);

	public Integer update(Warehouse Warehouse);

	public Integer delete(Integer id);
	
	public Warehouse findById(Integer id);

}
