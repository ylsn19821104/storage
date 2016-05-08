package com.l1.dao;

import com.l1.entity.ReturnDtl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface ReturnDtlDao {
	public List<ReturnDtl> find(Map<String, Object> map);

	public List<ReturnDtl> findByIds(String ids);

	public ReturnDtl findById(Integer id);

	public List<String> findNamesByIds(String ids);

	public Long getTotal(Map<String, Object> map);

	public Integer add(ReturnDtl color);

	public Integer update(ReturnDtl color);

	public Integer deleteById(Integer id);

	public void delete(String[] ids);

}
