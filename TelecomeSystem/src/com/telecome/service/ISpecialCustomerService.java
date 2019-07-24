package com.telecome.service;

import java.sql.SQLException;

import com.telecome.po.SpecialCustomer;
import com.telecome.utils.PageBean;

public interface ISpecialCustomerService {

	int updateToReserve(String phone, String name, String customerType, String address, String id, String manager)throws SQLException;

	int addToReserve(String phone, String name, String customerType, String address, String manager) throws SQLException;

	PageBean getTotalNotice(PageBean pageBean)throws SQLException;

	PageBean query(PageBean pageBean)throws SQLException;

	int delete(String id)throws SQLException;

	SpecialCustomer queryById(String id)throws SQLException;
}
