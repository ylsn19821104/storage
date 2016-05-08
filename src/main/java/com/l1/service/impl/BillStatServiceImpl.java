package com.l1.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.l1.dao.BillStatDao;
import com.l1.entity.BillStat;
import com.l1.service.BillStatService;

@Service("billStatService")
public class BillStatServiceImpl implements BillStatService {
	@Resource
	private BillStatDao billstatDao;

	@Override
	public List<BillStat> find(Map<String, Object> map) {
		return billstatDao.find(map);
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		return billstatDao.getTotal(map);
	}

	@Override
	public Integer add(BillStat billstat) {
		return billstatDao.add(billstat);
	}

	@Override
	public Integer update(BillStat billstat) {
		return billstatDao.update(billstat);
	}

	@Override
	public Integer delete(Integer id) {
		return billstatDao.delete(id);
	}

	@Override
	public List<BillStat> findByIds(String ids) {
		return billstatDao.findByIds(ids);
	}

	@Override
	public List<String> findNamesByIds(String ids) {
		return billstatDao.findNamesByIds(ids);
	}

	@Override
	public BillStat findById(Integer id) {
		return billstatDao.findById(id);
	}

}
