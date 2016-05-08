package com.l1.service;

import java.util.List;
import java.util.Map;

import com.l1.entity.YcckDtl;

public interface YcckDtlService {
	public List<YcckDtl> find(Map<String, Object> map);

	public List<YcckDtl> findByIds(String ids);

	public YcckDtl findById(Integer id);

	public List<String> findNamesByIds(String ids);

	public Long getTotal(Map<String, Object> map);

	public Integer add(YcckDtl Purchase);

	public Integer update(YcckDtl Purchase);

	public Integer deleteById(Integer id);

	public void delete(String[] ids);
}
