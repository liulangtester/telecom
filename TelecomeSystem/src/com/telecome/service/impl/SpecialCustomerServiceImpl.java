package com.telecome.service.impl;

import java.sql.SQLException;

import com.telecome.dao.ISpecialCustomerDao;
import com.telecome.dao.impl.SpecialCustomerDaoImpl;
import com.telecome.po.SpecialCustomer;
import com.telecome.service.ISpecialCustomerService;
import com.telecome.utils.PageBean;

public class SpecialCustomerServiceImpl implements ISpecialCustomerService {
	private ISpecialCustomerDao specialCustomerDao = new SpecialCustomerDaoImpl();
	@Override
	public int updateToReserve(String phone, String name, String customerType, String address, String id,
			String manager) throws SQLException {
		return specialCustomerDao.updateToReserve(phone, name, customerType, address, id, manager);
	}
	@Override
	public int addToReserve(String phone, String name, String customerType, String address, String manager)
			throws SQLException {
		return specialCustomerDao.addToReserve(phone, name, customerType, address, manager);
	}
	@Override
	public PageBean getTotalNotice(PageBean pageBean) throws SQLException {
		return specialCustomerDao.getTotalNotice(pageBean);
	}
	@Override
	public PageBean query(PageBean pageBean) throws SQLException {
		return specialCustomerDao.query(pageBean);
	}
	@Override
	public int delete(String id) throws SQLException {
		return specialCustomerDao.delete(id);
	}
	@Override
	public SpecialCustomer queryById(String id) throws SQLException {
		return specialCustomerDao.queryById(id);
	}

}
