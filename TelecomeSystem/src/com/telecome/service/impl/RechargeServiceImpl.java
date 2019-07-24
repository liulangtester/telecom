package com.telecome.service.impl;

import java.sql.SQLException;

import com.telecome.dao.IRechargeDao;
import com.telecome.dao.impl.RechargeDaoImpl;
import com.telecome.po.User;
import com.telecome.service.IRechargeService;

public class RechargeServiceImpl implements IRechargeService {
	private IRechargeDao rechargeDao = new RechargeDaoImpl();
	@Override
	public int addRecord(String money, String account, String rechargeNumber, User user) throws SQLException {
		return rechargeDao.addRecharge(money, account, rechargeNumber, user);
	}

}
