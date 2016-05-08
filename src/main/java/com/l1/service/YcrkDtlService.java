package com.l1.service;

import java.util.List;
import java.util.Map;

import com.l1.entity.YcrkDtl;

public interface YcrkDtlService {
	public List<YcrkDtl> find(Map<String, Object> map);

	public List<YcrkDtl> findByIds(String ids);

	public YcrkDtl findById(Integer id);

	public List<String> findNamesByIds(String ids);

	public Long getTotal(Map<String, Object> map);

	public Integer add(YcrkDtl Purchase);

	public Integer update(YcrkDtl Purchase);

	public Integer deleteById(Integer id);

	public void delete(String[] ids);
}
