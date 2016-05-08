package com.l1.service;

import java.util.List;
import java.util.Map;

import com.l1.entity.Size;

public interface SizeService {
	public List<Size> find(Map<String, Object> map);

	public List<Size> findByIds(String ids);

	public Size findById(Integer id);

	public List<String> findNamesByIds(String ids);

	public Long getTotal(Map<String, Object> map);

	public Integer add(Size Size);

	public Integer update(Size Size);

	public Integer delete(Integer id);
}
