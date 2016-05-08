package com.l1.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.l1.dao.WarehouseLocationDao;
import com.l1.entity.WarehouseLocation;
import com.l1.service.WarehouseLocationService;

@Service("warehouseLocationService")
public class WarehouseLocationServiceImpl implements WarehouseLocationService {
	@Resource
	private WarehouseLocationDao warehouseLocationDao;

	@Override
	public List<WarehouseLocation> find(Map<String, Object> map) {
		return warehouseLocationDao.find(map);
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		return warehouseLocationDao.getTotal(map);
	}

	@Override
	public Integer add(WarehouseLocation size) {
		return warehouseLocationDao.add(size);
	}

	@Override
	public Integer update(WarehouseLocation size) {
		return warehouseLocationDao.update(size);
	}

	@Override
	public Integer delete(Integer id) {
		return warehouseLocationDao.delete(id);
	}

	@Override
	public List<WarehouseLocation> findByIds(String ids) {
		return warehouseLocationDao.findByIds(ids);
	}

	@Override
	public List<String> findNamesByIds(String ids) {
		return warehouseLocationDao.findNamesByIds(ids);
	}

	@Override
	public WarehouseLocation findById(Integer id) {
		return warehouseLocationDao.findById(id);
	}

}
