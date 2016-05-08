package com.l1.service;

import java.util.List;
import java.util.Map;

import com.l1.entity.SizeDtl;

public interface SizeDtlService {
	public List<SizeDtl> find(Map<String, Object> map);

	public List<SizeDtl> findByIds(String ids);

	public SizeDtl findById(Integer id);

	public List<String> findNamesByIds(String[] ids);

	public Long getTotal(Map<String, Object> map);

	public Integer add(SizeDtl Size);

	public Integer update(SizeDtl Size);

	public Integer delete(Integer id);
}
