package com.l1.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.l1.dao.YcrkDtlDao;
import com.l1.entity.YcrkDtl;
import com.l1.service.YcrkDtlService;

@Service("ycrkDtlService")
public class YcrkDtlServiceImpl implements YcrkDtlService {
	@Resource
	private YcrkDtlDao ycrkDtlDao;

	@Override
	public List<YcrkDtl> find(Map<String, Object> map) {
		return ycrkDtlDao.find(map);
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		return ycrkDtlDao.getTotal(map);
	}

	@Override
	public Integer add(YcrkDtl ycrkDtl) {
		return ycrkDtlDao.add(ycrkDtl);
	}

	@Override
	public Integer update(YcrkDtl ycrkDtl) {
		return ycrkDtlDao.update(ycrkDtl);
	}

	@Override
	public Integer deleteById(Integer id) {
		return ycrkDtlDao.deleteById(id);
	}

	@Override
	public List<YcrkDtl> findByIds(String ids) {
		return ycrkDtlDao.findByIds(ids);
	}

	@Override
	public List<String> findNamesByIds(String ids) {
		return ycrkDtlDao.findNamesByIds(ids);
	}

	@Override
  public YcrkDtl findById(Integer id) {
		return ycrkDtlDao.findById(id);
	}

  @Override
  public void delete(String[] ids) {
      ycrkDtlDao.delete(ids);
  }

}
