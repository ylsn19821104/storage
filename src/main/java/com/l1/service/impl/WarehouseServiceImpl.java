package com.l1.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.l1.dao.WarehouseDao;
import com.l1.entity.Warehouse;
import com.l1.service.WarehouseService;

@Service("warehouseService")
public class WarehouseServiceImpl implements WarehouseService {
	@Resource
	private WarehouseDao warehouseDao;

	@Override
	public List<Warehouse> find(Map<String, Object> map) {
		return warehouseDao.find(map);
	}

    @Override
    public List<Map<String,String>> findForCombo(Map<String, Object> map){
        return warehouseDao.findForCombo(map);
    }

    @Override
	public Long getTotal(Map<String, Object> map) {
		return warehouseDao.getTotal(map);
	}

	@Override
	public Integer add(Warehouse Warehouse) {
		return warehouseDao.add(Warehouse);
	}

	@Override
	public Integer update(Warehouse Warehouse) {
		return warehouseDao.update(Warehouse);
	}

	@Override
	public Integer delete(Integer id) {
		return warehouseDao.delete(id);
	}

	@Override
	public Warehouse findById(Integer id) {
		return warehouseDao.findById(id);
	}

}
