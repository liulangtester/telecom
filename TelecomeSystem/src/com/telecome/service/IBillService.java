package com.telecome.service;

import java.sql.SQLException;

import com.telecome.po.Bill;
import com.telecome.po.User;
import com.telecome.utils.PageBean;

public interface IBillService {
	/**
	 * 获得通话记录账单以及账户余额
	 * @param bill
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	int getBill(Bill bill, User user) throws SQLException;

	PageBean getTotalNotice(PageBean pageBean)throws SQLException;

	PageBean query(PageBean pageBean)throws SQLException;


}
