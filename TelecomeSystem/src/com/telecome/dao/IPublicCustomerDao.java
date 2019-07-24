package com.telecome.dao;

import java.sql.SQLException;

import com.telecome.po.PublicCustomer;
import com.telecome.utils.PageBean;

public interface IPublicCustomerDao {

	int addToReserve(String phone, String name, String customerType, String address) throws SQLException;

	int updateToReserve(String phone, String name, String customerType, String address, String id)throws SQLException;

	PageBean getTotalNotice(PageBean pageBean)throws SQLException;

	PageBean query(PageBean pageBean)throws SQLException;

	int delete(String id)throws SQLException;

	PublicCustomer queryById(String id)throws SQLException;

}
