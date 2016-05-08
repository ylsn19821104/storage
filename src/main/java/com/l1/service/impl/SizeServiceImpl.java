package com.l1.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.l1.dao.SizeDao;
import com.l1.entity.Size;
import com.l1.service.SizeService;

@Service("sizeService")
public class SizeServiceImpl implements SizeService {
	@Resource
	private SizeDao sizeDao;

	@Override
	public List<Size> find(Map<String, Object> map) {
		return sizeDao.find(map);
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		return sizeDao.getTotal(map);
	}

	@Override
	public Integer add(Size size) {
		return sizeDao.add(size);
	}

	@Override
	public Integer update(Size size) {
		return sizeDao.update(size);
	}

	@Override
	public Integer delete(Integer id) {
		return sizeDao.delete(id);
	}

	@Override
	public List<Size> findByIds(String ids) {
		return sizeDao.findByIds(ids);
	}

	@Override
	public List<String> findNamesByIds(String ids) {
		return sizeDao.findNamesByIds(ids);
	}

	@Override
	public Size findById(Integer id) {
		return sizeDao.findById(id);
	}

}
