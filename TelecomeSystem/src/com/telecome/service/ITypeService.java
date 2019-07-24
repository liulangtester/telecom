package com.telecome.service;

import java.sql.SQLException;
import java.util.List;

import com.telecome.po.CustomerType;

public interface ITypeService {

	List getCustomerType() throws SQLException;

}
