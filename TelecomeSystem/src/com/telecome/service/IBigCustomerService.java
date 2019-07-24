package com.telecome.service;

import java.sql.SQLException;

import com.telecome.po.BigCustomer;
import com.telecome.po.PublicCustomer;
import com.telecome.utils.PageBean;

public interface IBigCustomerService {

	int updateToReserve(String phone, String name, String customerType, String address, String id, String manager,
			String industry, String tc) throws SQLException;

	int addToReserve(String phone, String name, String customerType, String address, String manager, String industry,
			String tc)throws SQLException;

	PageBean getTotalNotice(PageBean pageBean)throws SQLException;

	PageBean query(PageBean pageBean)throws SQLException;

	int delete(String id)throws SQLException;

	BigCustomer queryById(String id)throws SQLException;

}
