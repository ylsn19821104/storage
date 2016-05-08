package com.l1.dao;

import com.l1.entity.Warehouse;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface WarehouseDao {
	public List<Warehouse> find(Map<String, Object> map);

    public List<Map<String,String>> findForCombo(Map<String, Object> map);

	public Long getTotal(Map<String, Object> map);

	public Integer add(Warehouse Warehouse);

	public Integer update(Warehouse Warehouse);

	public Integer delete(Integer id);

	public Warehouse findById(Integer id);

}
