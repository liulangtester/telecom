package com.telecome.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.telecome.dao.ITypeDao;
import com.telecome.dao.impl.TypeDaoImpl;
import com.telecome.po.CustomerType;
import com.telecome.service.ITypeService;

public class TypeServiceImpl implements ITypeService {
	ITypeDao typeDao = new TypeDaoImpl();
	@Override
	public List getCustomerType() throws SQLException {
		return typeDao.getCustomerType();
	}

}
