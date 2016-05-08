package com.l1.service;

import java.util.List;
import java.util.Map;

import com.l1.entity.Brand;

public interface BrandService {
	public List<Brand> find(Map<String, Object> map);

	public Long getTotal(Map<String, Object> map);

	public Integer add(Brand brand);

	public Integer update(Brand brand);

	public Integer delete(Integer id);

	public Brand findById(Integer id);

}
