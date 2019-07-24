package com.telecome.service;

import java.sql.SQLException;

import com.telecome.po.Account;
import com.telecome.po.User;
import com.telecome.utils.PageBean;

public interface IUserService {
/**
 * 通过用户名和密码匹配获得user 用于登录
 * @param username
 * @param password
 * @return
 * @throws SQLException
 */
	public User getUser(String username, String password) throws SQLException;

/**
 * 通过用户名匹配获得user 用于更新session
 * @param username
 * @return
 * @throws SQLException
 */
	public User getUser(String username) throws SQLException;

	public int addToReserve(String phone, String address, String account, String password, String remain_money,
		String type)throws SQLException;

	public PageBean getTotalNotice(PageBean pageBean)throws SQLException;

	public PageBean query(PageBean pageBean)throws SQLException;

	public int delete(String id)throws SQLException;

	public User queryById(String id)throws SQLException;

	public int updateToReserve(String phone, String address, String account, String remain_money, String type, String id)throws SQLException;

	}
