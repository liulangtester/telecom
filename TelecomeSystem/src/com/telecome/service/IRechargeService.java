package com.telecome.service;

import java.sql.SQLException;

import com.telecome.po.User;

public interface IRechargeService {
/**
 * Ð´Èë³äÖµ¼ÇÂ¼
 * @param money
 * @param account
 * @param rechargeNumber
 * @param user
 * @return
 * @throws SQLException
 */
	int addRecord(String money, String account, String rechargeNumber, User user) throws SQLException;

}
