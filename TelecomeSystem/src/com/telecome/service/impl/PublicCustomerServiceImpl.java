package com.telecome.service.impl;

import java.sql.SQLException;

import com.telecome.dao.IPublicCustomerDao;
import com.telecome.dao.impl.PublicCustomerDaoImpl;
import com.telecome.po.PublicCustomer;
import com.telecome.service.IPublicCustomerService;
import com.telecome.utils.PageBean;

public class PublicCustomerServiceImpl implements IPublicCustomerService {
	private IPublicCustomerDao publicCustomerDao = new PublicCustomerDaoImpl();
	@Override
	public int addToReserve(String phone, String name, String customerType, String address) throws SQLException {
		return publicCustomerDao.addToReserve(phone, name, customerType, address);
	}
	@Override
	public int updateToReserve(String phone, String name, String customerType, String address, String id)
			throws SQLException {
		return publicCustomerDao.updateToReserve(phone, name, customerType, address, id);
	}
	@Override
	public PageBean getTotalNotice(PageBean pageBean) throws SQLException {
		return publicCustomerDao.getTotalNotice(pageBean);
	}
	@Override
	public PageBean query(PageBean pageBean) throws SQLException {
		return publicCustomerDao.query(pageBean);
	}
	@Override
	public int delete(String id) throws SQLException {
		return publicCustomerDao.delete(id);
	}
	@Override
	public PublicCustomer queryById(String id) throws SQLException {
		return publicCustomerDao.queryById(id);
	}

}
