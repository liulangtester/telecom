package com.telecome.service;

import java.sql.SQLException;
import java.util.List;

import com.telecome.po.BusinessCustomer;
import com.telecome.utils.PageBean;

public interface IBusinessCustomerService {

	int addToReserve(String phone, String name, String customerType, String address, 
			String manager, String industry) throws SQLException;

	PageBean query(PageBean pageBean) throws SQLException;

	List queryByPart(String content)throws SQLException;

	int delete(String id)throws SQLException;

	BusinessCustomer queryById(String id)throws SQLException;

	int updateToReserve(String phone, String name, String customerType, String address, String manager,
			String industry, String id)throws SQLException;

	PageBean getTotalNotice(PageBean pageBean)throws SQLException;

}
