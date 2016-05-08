package com.l1.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.l1.dao.PurchaseDtlDao;
import com.l1.entity.PurchaseDtl;
import com.l1.service.PurchaseDtlService;

@Service("purchaseDtlService")
public class PurchaseDtlServiceImpl implements PurchaseDtlService {
	@Resource
	private PurchaseDtlDao purchaseDtlDao;

	@Override
	public List<PurchaseDtl> find(Map<String, Object> map) {
		return purchaseDtlDao.find(map);
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		return purchaseDtlDao.getTotal(map);
	}

	@Override
	public Integer add(PurchaseDtl purchaseDtl) {
		return purchaseDtlDao.add(purchaseDtl);
	}

	@Override
	public Integer update(PurchaseDtl purchaseDtl) {
		return purchaseDtlDao.update(purchaseDtl);
	}

	@Override
	public Integer deleteById(Integer id) {
		return purchaseDtlDao.deleteById(id);
	}

	@Override
	public List<PurchaseDtl> findByIds(String ids) {
		return purchaseDtlDao.findByIds(ids);
	}

	@Override
	public List<String> findNamesByIds(String ids) {
		return purchaseDtlDao.findNamesByIds(ids);
	}

	@Override
  public PurchaseDtl findById(Integer id) {
		return purchaseDtlDao.findById(id);
	}

  @Override
  public void delete(String[] ids) {
      purchaseDtlDao.delete(ids);
  }

}
