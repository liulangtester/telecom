package com.telecome.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.telecome.dao.ICallRecordDao;
import com.telecome.dao.impl.CallRecordDaoImpl;
import com.telecome.po.Bill;
import com.telecome.po.User;
import com.telecome.service.ICallRecordService;
import com.telecome.utils.PageBean;

public class CallRecordServiceImpl implements ICallRecordService {
	ICallRecordDao callRecordDao = new CallRecordDaoImpl();
//	@Override
//	public List getBill(User user) throws SQLException {
//		return callRecordDao.getBill(user);
//	}
//	@Override
//	public List getCallRecordByPart(String content) throws SQLException {
//		return callRecordDao.getCallRecordByPart(content);
//	}
	@Override
	public PageBean getTotalNotice(PageBean pageBean, User user) throws SQLException {
		return callRecordDao.getTotalNotice(pageBean,user);
	}
	@Override
	public PageBean query(PageBean pageBean, User user) throws SQLException {
		return callRecordDao.query(pageBean,user);
	}

}
