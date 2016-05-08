package com.l1.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.l1.dao.YcckDtlDao;
import com.l1.entity.YcckDtl;
import com.l1.service.YcckDtlService;

@Service("ycckDtlService")
public class YcckDtlServiceImpl implements YcckDtlService {
	@Resource
	private YcckDtlDao ycckDtlDao;

	@Override
	public List<YcckDtl> find(Map<String, Object> map) {
		return ycckDtlDao.find(map);
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		return ycckDtlDao.getTotal(map);
	}

	@Override
	public Integer add(YcckDtl ycckDtl) {
		return ycckDtlDao.add(ycckDtl);
	}

	@Override
	public Integer update(YcckDtl ycckDtl) {
		return ycckDtlDao.update(ycckDtl);
	}

	@Override
	public Integer deleteById(Integer id) {
		return ycckDtlDao.deleteById(id);
	}

	@Override
	public List<YcckDtl> findByIds(String ids) {
		return ycckDtlDao.findByIds(ids);
	}

	@Override
	public List<String> findNamesByIds(String ids) {
		return ycckDtlDao.findNamesByIds(ids);
	}

	@Override
  public YcckDtl findById(Integer id) {
		return ycckDtlDao.findById(id);
	}

  @Override
  public void delete(String[] ids) {
      ycckDtlDao.delete(ids);
  }

}
