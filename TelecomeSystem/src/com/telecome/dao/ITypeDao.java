package com.telecome.dao;

import java.sql.SQLException;
import java.util.List;

import com.telecome.po.CustomerType;

public interface ITypeDao {

	List getCustomerType() throws SQLException;

}
