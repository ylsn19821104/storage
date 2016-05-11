package com.l1.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.l1.dao.RentDtlDao;
import com.l1.entity.RentDtl;
import com.l1.service.RentDtlService;

@Service("rentDtlService")
public class RentDtlServiceImpl implements RentDtlService {
	@Resource
	private RentDtlDao rentDtlDao;

	@Override
	public List<RentDtl> find(Map<String, Object> map) {
		return rentDtlDao.find(map);
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		return rentDtlDao.getTotal(map);
	}

	@Override
	public Integer add(RentDtl rentDtl) {
		return rentDtlDao.add(rentDtl);
	}

	@Override
	public Integer update(RentDtl rentDtl) {
		return rentDtlDao.update(rentDtl);
	}

	@Override
	public Integer deleteById(Integer id) {
		return rentDtlDao.deleteById(id);
	}

	@Override
	public List<RentDtl> findByIds(String ids) {
		return rentDtlDao.findByIds(ids);
	}

	@Override
	public List<String> findNamesByIds(String ids) {
		return rentDtlDao.findNamesByIds(ids);
	}

	@Override
  public RentDtl findById(Integer id) {
		return rentDtlDao.findById(id);
	}

  @Override
  public void delete(Integer[] ids) {
      rentDtlDao.delete(ids);
  }

}
