package com.l1.service;

import java.util.List;
import java.util.Map;

import com.l1.entity.PurchaseDtl;

public interface PurchaseDtlService {
	public List<PurchaseDtl> find(Map<String, Object> map);

	public List<PurchaseDtl> findByIds(String ids);

	public PurchaseDtl findById(Integer id);

	public List<String> findNamesByIds(String ids);

	public Long getTotal(Map<String, Object> map);

	public Integer add(PurchaseDtl Purchase);

	public Integer update(PurchaseDtl Purchase);

	public Integer deleteById(Integer id);

	public void delete(String[] ids);
}
