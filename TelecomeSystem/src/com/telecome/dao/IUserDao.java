package com.telecome.dao;

import java.sql.SQLException;
import java.util.List;

import com.telecome.po.User;
import com.telecome.utils.PageBean;

public interface IUserDao<T> {

	public User getUser(String username, String passwords)throws SQLException ;

	public User getUser(String username) throws SQLException;

	public int addToReserve(String phone, String address, String account, String password, String remain_money,
			String type)throws SQLException;

	public PageBean getTotalNotice(PageBean pageBean)throws SQLException;

	public PageBean query(PageBean pageBean)throws SQLException;

	public int delete(String id)throws SQLException;

	public User queryById(String id)throws SQLException;

	public int updateToReserve(String phone, String address, String account, String remain_money, String type, String id)throws SQLException;

}
