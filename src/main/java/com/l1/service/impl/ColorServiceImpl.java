package com.l1.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.l1.dao.ColorDao;
import com.l1.entity.Color;
import com.l1.service.ColorService;

@Service("colorService")
public class ColorServiceImpl implements ColorService {
	@Resource
	private ColorDao colorDao;

	@Override
	public List<Color> find(Map<String, Object> map) {
		return colorDao.find(map);
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		return colorDao.getTotal(map);
	}

	@Override
	public Integer add(Color color) {
		return colorDao.add(color);
	}

	@Override
	public Integer update(Color color) {
		return colorDao.update(color);
	}

	@Override
	public Integer delete(Integer id) {
		return colorDao.delete(id);
	}

	@Override
	public List<Color> findByIds(String ids) {
		return colorDao.findByIds(ids);
	}

	@Override
	public List<String> findNamesByIds(String[] ids) {
		return colorDao.findNamesByIds(ids);
	}

	@Override
	public Color findById(Integer id) {
		return colorDao.findById(id);
	}

}
