package com.telecome.dao;

import java.sql.SQLException;

import com.telecome.po.Bill;
import com.telecome.po.User;
import com.telecome.utils.PageBean;

public interface IBillDao {

	int getBill(Bill bill, User user) throws SQLException;

	PageBean getTotalNotice(PageBean pageBean)throws SQLException;

	PageBean query(PageBean pageBean)throws SQLException;


}
