package com.l1.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.l1.dao.BrandDao;
import com.l1.entity.Brand;
import com.l1.service.BrandService;

@Service("brandService")
public class BrandServiceImpl implements BrandService {
	@Resource
	private BrandDao brandDao;

	@Override
	public List<Brand> find(Map<String, Object> map) {
		return brandDao.find(map);
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		return brandDao.getTotal(map);
	}

	@Override
	public Integer add(Brand brand) {
		return brandDao.add(brand);
	}

	@Override
	public Integer update(Brand brand) {
		return brandDao.update(brand);
	}

	@Override
	public Integer delete(Integer id) {
		return brandDao.delete(id);
	}

	@Override
	public Brand findById(Integer id) {
		return brandDao.findById(id);
	}

}
