package com.l1.dao;

import com.l1.entity.SizeDtl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface SizeDtlDao {
	public List<SizeDtl> find(Map<String, Object> map);

	public List<SizeDtl> findByIds(String ids);

	public SizeDtl findById(Integer id);

	public List<String> findNamesByIds(String[] ids);

	public Long getTotal(Map<String, Object> map);

	public Integer add(SizeDtl color);

	public Integer update(SizeDtl color);

	public Integer delete(Integer id);

}
