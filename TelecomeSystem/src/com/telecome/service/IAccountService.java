package com.telecome.service;

import java.sql.SQLException;

import com.telecome.po.Account;
import com.telecome.po.SpecialCustomer;
import com.telecome.utils.PageBean;

public interface IAccountService {

	int addToReserve(String phone, String name, String account, String bank, String s_bank, String remain_money,
			String total_cost, String month_cost) throws SQLException;

	int updateToReserve(String phone, String name, String account, String bank, String s_bank, String remain_money,
			String total_cost, String month_cost, String id)throws SQLException;

	PageBean getTotalNotice(PageBean pageBean)throws SQLException;

	PageBean query(PageBean pageBean)throws SQLException;

	Account queryById(String id)throws SQLException;

	int delete(String id)throws SQLException;

}
