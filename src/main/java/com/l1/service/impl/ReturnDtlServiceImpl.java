package com.l1.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.l1.dao.ReturnDtlDao;
import com.l1.entity.ReturnDtl;
import com.l1.service.ReturnDtlService;

@Service("returnDtlService")
public class ReturnDtlServiceImpl implements ReturnDtlService {
	@Resource
	private ReturnDtlDao returnDtlDao;

	@Override
	public List<ReturnDtl> find(Map<String, Object> map) {
		return returnDtlDao.find(map);
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		return returnDtlDao.getTotal(map);
	}

	@Override
	public Integer add(ReturnDtl returnDtl) {
		return returnDtlDao.add(returnDtl);
	}

	@Override
	public Integer update(ReturnDtl returnDtl) {
		return returnDtlDao.update(returnDtl);
	}

	@Override
	public Integer deleteById(Integer id) {
		return returnDtlDao.deleteById(id);
	}

	@Override
	public List<ReturnDtl> findByIds(String ids) {
		return returnDtlDao.findByIds(ids);
	}

	@Override
	public List<String> findNamesByIds(String ids) {
		return returnDtlDao.findNamesByIds(ids);
	}

	@Override
  public ReturnDtl findById(Integer id) {
		return returnDtlDao.findById(id);
	}

  @Override
  public void delete(String[] ids) {
		returnDtlDao.delete(ids);
  }

}
