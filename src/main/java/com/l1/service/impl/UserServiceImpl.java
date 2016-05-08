package com.l1.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.l1.dao.UserDao;
import com.l1.entity.User;
import com.l1.service.UserService;

/**
 * 用户Service实现类
 * 
 * @author hongxp
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;

	@Override
	public User login(User user) {
		return userDao.login(user);
	}

	@Override
	public List<User> find(Map<String, Object> map) {
		return userDao.find(map);
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		return userDao.getTotal(map);
	}

	@Override
	public int update(User user) {
		return userDao.update(user);
	}

	@Override
	public int add(User user) {
		return userDao.add(user);
	}

	@Override
	public int delete(Integer id) {
		return userDao.delete(id);
	}

	@Override
	public List<User> findForCombo(Map<String, Object> map) {
		return userDao.findForCombo(map);
	}
}
