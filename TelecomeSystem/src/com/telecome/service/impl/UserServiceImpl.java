package com.telecome.service.impl;

import java.sql.SQLException;

import com.telecome.dao.IUserDao;
import com.telecome.dao.impl.userDaoImpl;
import com.telecome.po.User;
import com.telecome.service.IUserService;
import com.telecome.utils.PageBean;

public class UserServiceImpl implements IUserService {
	private IUserDao userDao = new userDaoImpl();
	@Override
	public User getUser(String username, String password) throws SQLException {
		return userDao.getUser(username,password);
	}
	@Override
	public User getUser(String username) throws SQLException {
		return userDao.getUser(username);
	}
	@Override
	public int addToReserve(String phone, String address, String account, String password, String remain_money,
			String type) throws SQLException {
		return userDao.addToReserve(phone, address, account, password, remain_money, type);
	}
	@Override
	public PageBean getTotalNotice(PageBean pageBean) throws SQLException {
		return userDao.getTotalNotice(pageBean);
	}
	@Override
	public PageBean query(PageBean pageBean) throws SQLException {
		return userDao.query(pageBean);
	}
	@Override
	public int delete(String id) throws SQLException {
		return userDao.delete(id);
	}
	@Override
	public User queryById(String id) throws SQLException {
		return userDao.queryById(id);
	}
	@Override
	public int updateToReserve(String phone, String address, String account, String remain_money, String type, String id)
			throws SQLException {
		return userDao.updateToReserve(phone, address, account, remain_money, type, id);
	}

}
