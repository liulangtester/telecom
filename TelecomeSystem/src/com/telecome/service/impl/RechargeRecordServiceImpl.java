package com.telecome.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.telecome.dao.IRechargeRecordDao;
import com.telecome.dao.impl.RechargeRecordDaoImpl;
import com.telecome.po.User;
import com.telecome.service.IRechargeRecordService;
import com.telecome.utils.PageBean;

public class RechargeRecordServiceImpl implements IRechargeRecordService {
	private IRechargeRecordDao rechargeRecordDao = new RechargeRecordDaoImpl();
	@Override
	public PageBean getTotalNotice(PageBean pageBean, User user) throws SQLException {
		return rechargeRecordDao.getTotalNotice(pageBean, user);
	}
	@Override
	public PageBean query(PageBean pageBean, User user) throws SQLException {
		return rechargeRecordDao.query(pageBean, user);
	}
	@Override
	public PageBean getTotalNotice(PageBean pageBean) throws SQLException {
		return rechargeRecordDao.getTotalNotice(pageBean);
	}
	@Override
	public PageBean query(PageBean pageBean) throws SQLException {
		return rechargeRecordDao.query(pageBean);
	}

}
