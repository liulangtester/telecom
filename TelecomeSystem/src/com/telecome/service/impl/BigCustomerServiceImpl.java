package com.telecome.service.impl;

import java.sql.SQLException;

import com.telecome.dao.IBigCustomerDao;
import com.telecome.dao.impl.BigCustomerDaoImpl;
import com.telecome.po.BigCustomer;
import com.telecome.po.PublicCustomer;
import com.telecome.service.IBigCustomerService;
import com.telecome.utils.PageBean;

public class BigCustomerServiceImpl implements IBigCustomerService {
	IBigCustomerDao bigCustomerDao = new BigCustomerDaoImpl();
	@Override
	public int updateToReserve(String phone, String name, String customerType, String address, String id,
			String manager, String industry, String tc)throws SQLException {
		return bigCustomerDao.updateToReserve(phone, name, customerType, address, id, manager, industry,tc);
	}
	@Override
	public int addToReserve(String phone, String name, String customerType, String address, String manager,
			String industry, String tc) throws SQLException {
		return  bigCustomerDao.addToReserve(phone, name, customerType, address, manager, industry, tc);
	}
	@Override
	public PageBean getTotalNotice(PageBean pageBean) throws SQLException {
		return bigCustomerDao.getTotalNotice(pageBean);
	}
	@Override
	public PageBean query(PageBean pageBean) throws SQLException {
		return bigCustomerDao.query(pageBean);
	}
	@Override
	public int delete(String id) throws SQLException {
		return bigCustomerDao.delete(id);
	}
	@Override
	public BigCustomer queryById(String id) throws SQLException {
		return bigCustomerDao.queryById(id);
	}

}
