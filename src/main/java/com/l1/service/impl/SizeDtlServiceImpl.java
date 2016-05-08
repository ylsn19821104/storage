package com.l1.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.l1.dao.SizeDtlDao;
import com.l1.entity.SizeDtl;
import com.l1.service.SizeDtlService;

@Service("sizeDtlService")
public class SizeDtlServiceImpl implements SizeDtlService {
	@Resource
	private SizeDtlDao sizeDtlDao;

	@Override
	public List<SizeDtl> find(Map<String, Object> map) {
		return sizeDtlDao.find(map);
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		return sizeDtlDao.getTotal(map);
	}

	@Override
	public Integer add(SizeDtl size) {
		return sizeDtlDao.add(size);
	}

	@Override
	public Integer update(SizeDtl size) {
		return sizeDtlDao.update(size);
	}

	@Override
	public Integer delete(Integer id) {
		return sizeDtlDao.delete(id);
	}

	@Override
	public List<SizeDtl> findByIds(String ids) {
		return sizeDtlDao.findByIds(ids);
	}

	@Override
	public List<String> findNamesByIds(String[] ids) {
		return sizeDtlDao.findNamesByIds(ids);
	}

	@Override
	public SizeDtl findById(Integer id) {
		return sizeDtlDao.findById(id);
	}

}
