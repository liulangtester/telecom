package com.telecome.service.impl;

import java.sql.SQLException;

import com.telecome.dao.IBillDao;
import com.telecome.dao.impl.BillDaoImpl;
import com.telecome.po.Bill;
import com.telecome.po.User;
import com.telecome.service.IBillService;
import com.telecome.utils.PageBean;

public class BillServiceImpl implements IBillService {
	private IBillDao billDao = new BillDaoImpl();
	@Override
	public int getBill(Bill bill, User user) throws SQLException {
		return billDao.getBill(bill, user);
	}
	@Override
	public PageBean getTotalNotice(PageBean pageBean) throws SQLException {
		return billDao.getTotalNotice(pageBean);
	}
	@Override
	public PageBean query(PageBean pageBean) throws SQLException {
		return billDao.query(pageBean);
	}

}
