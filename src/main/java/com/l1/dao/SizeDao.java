package com.l1.dao;

import com.l1.entity.Size;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface SizeDao {
	public List<Size> find(Map<String, Object> map);

	public List<Size> findByIds(String ids);

	public Size findById(Integer id);

	public List<String> findNamesByIds(String ids);

	public Long getTotal(Map<String, Object> map);

	public Integer add(Size color);

	public Integer update(Size color);

	public Integer delete(Integer id);

}
