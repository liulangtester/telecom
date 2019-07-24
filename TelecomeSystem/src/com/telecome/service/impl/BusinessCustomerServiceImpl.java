package com.telecome.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.telecome.dao.IBusinessCustomerDao;
import com.telecome.dao.impl.BusinessCustomerDaoImpl;
import com.telecome.po.BusinessCustomer;
import com.telecome.service.IBusinessCustomerService;
import com.telecome.utils.PageBean;

public class BusinessCustomerServiceImpl implements IBusinessCustomerService {
	private IBusinessCustomerDao businessCustomerDao = new BusinessCustomerDaoImpl();
	@Override
	public int addToReserve(String phone, String name, String customerType, String address, String manager,
			String industry) throws SQLException {

		return businessCustomerDao.addToReserve(phone,name,customerType,address,manager,industry);
	}
	@Override
	public PageBean query(PageBean pageBean) throws SQLException {
		return businessCustomerDao.query(pageBean);
	}
	@Override
	public List queryByPart(String content) throws SQLException {
		return businessCustomerDao.queryByPart(content);
	}
	@Override
	public int delete(String id) throws SQLException {
		return businessCustomerDao.delete(id);
	}
	@Override
	public BusinessCustomer queryById(String id) throws SQLException {
		return businessCustomerDao.queryById(id);
	}
	@Override
	public int updateToReserve(String phone, String name, String customerType, String address, String manager,
			String industry, String id) throws SQLException {
		return businessCustomerDao.updateToReserve(phone,name,customerType,address,manager,industry,id);
	}
	@Override
	public PageBean getTotalNotice(PageBean pageBean) throws SQLException {
		return businessCustomerDao.getTotalNotice(pageBean);
	}

}
