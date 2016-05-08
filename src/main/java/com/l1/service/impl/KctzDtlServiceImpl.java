package com.l1.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.l1.dao.KctzDtlDao;
import com.l1.entity.KctzDtl;
import com.l1.service.KctzDtlService;

@Service("kctzDtlService")
public class KctzDtlServiceImpl implements KctzDtlService {
	@Resource
	private KctzDtlDao kctzDtlDao;

	@Override
	public List<KctzDtl> find(Map<String, Object> map) {
		return kctzDtlDao.find(map);
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		return kctzDtlDao.getTotal(map);
	}

	@Override
	public Integer add(KctzDtl kctzDtl) {
		return kctzDtlDao.add(kctzDtl);
	}

	@Override
	public Integer update(KctzDtl kctzDtl) {
		return kctzDtlDao.update(kctzDtl);
	}

	@Override
	public Integer deleteById(Integer id) {
		return kctzDtlDao.deleteById(id);
	}

	@Override
	public List<KctzDtl> findByIds(String ids) {
		return kctzDtlDao.findByIds(ids);
	}

	@Override
	public List<String> findNamesByIds(String ids) {
		return kctzDtlDao.findNamesByIds(ids);
	}

	@Override
  public KctzDtl findById(Integer id) {
		return kctzDtlDao.findById(id);
	}

  @Override
  public void delete(String[] ids) {
      kctzDtlDao.delete(ids);
  }

}
