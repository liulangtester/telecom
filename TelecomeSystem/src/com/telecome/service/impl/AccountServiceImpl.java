package com.telecome.service.impl;

import java.sql.SQLException;

import com.telecome.dao.IAccountDao;
import com.telecome.dao.impl.AccountDaoImpl;
import com.telecome.po.Account;
import com.telecome.po.SpecialCustomer;
import com.telecome.service.IAccountService;
import com.telecome.utils.PageBean;

public class AccountServiceImpl implements IAccountService {
	private IAccountDao accountDao = new AccountDaoImpl();
	@Override
	public int addToReserve(String phone, String name, String account, String bank, String s_bank, String remain_money,
			String total_cost, String month_cost) throws SQLException {
		return accountDao.addToReserve(phone, name, account, bank, s_bank, remain_money, total_cost, month_cost);
	}
	@Override
	public int updateToReserve(String phone, String name, String account, String bank, String s_bank,
			String remain_money, String total_cost, String month_cost,String id) throws SQLException {
		return accountDao.updateToReserve(phone, name, account, bank, s_bank, remain_money, total_cost, month_cost,id);
	}
	@Override
	public PageBean getTotalNotice(PageBean pageBean) throws SQLException {
		return accountDao.getTotalNotice(pageBean);
	}
	@Override
	public PageBean query(PageBean pageBean) throws SQLException {
		return accountDao.query(pageBean);
	}
	@Override
	public Account queryById(String id) throws SQLException {
		return  accountDao.queryById(id);
	}
	@Override
	public int delete(String id) throws SQLException {
		return  accountDao.delete(id);
	}

}
