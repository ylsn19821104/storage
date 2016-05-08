package com.l1.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.l1.dao.MinorCategoryDao;
import com.l1.entity.MinorCategory;
import com.l1.service.MinorCategoryService;

@Service("minorCategoryService")
public class MinorCategoryServiceImpl implements MinorCategoryService {
	@Resource
	private MinorCategoryDao minorDao;

	@Override
	public List<MinorCategory> find(Map<String, Object> map) {
		return minorDao.find(map);
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		return minorDao.getTotal(map);
	}

	@Override
	public Integer add(MinorCategory minor) {
		return minorDao.add(minor);
	}

	@Override
	public Integer update(MinorCategory minor) {
		return minorDao.update(minor);
	}

	@Override
	public Integer delete(Integer id) {
		return minorDao.delete(id);
	}

  @Override
  public MinorCategory findById(Integer minorId) {
    return minorDao.findById(minorId);
  }

}
