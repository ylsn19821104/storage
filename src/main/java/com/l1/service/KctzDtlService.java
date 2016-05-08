package com.l1.service;

import java.util.List;
import java.util.Map;

import com.l1.entity.KctzDtl;

public interface KctzDtlService {
	public List<KctzDtl> find(Map<String, Object> map);

	public List<KctzDtl> findByIds(String ids);

	public KctzDtl findById(Integer id);

	public List<String> findNamesByIds(String ids);

	public Long getTotal(Map<String, Object> map);

	public Integer add(KctzDtl Purchase);

	public Integer update(KctzDtl Purchase);

	public Integer deleteById(Integer id);

	public void delete(String[] ids);
}
