package com.telecome.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.telecome.dao.IIndustryDao;
import com.telecome.dao.impl.IndustryDaoImpl;
import com.telecome.service.IIndustryService;

public class IndustryServiceImpl implements IIndustryService {
	IIndustryDao industryDao = new IndustryDaoImpl();
	@Override
	public List getIndustryType() throws SQLException {
		return industryDao.getIndustryType();
	}

}
