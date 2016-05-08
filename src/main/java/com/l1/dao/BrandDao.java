package com.l1.dao;

import com.l1.entity.Brand;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface BrandDao {
	
	public Brand findById(Integer id);
	
	public List<Brand> find(Map<String, Object> map);

	public Long getTotal(Map<String, Object> map);

	public Integer add(Brand color);

	public Integer update(Brand color);

	public Integer delete(Integer id);

}
