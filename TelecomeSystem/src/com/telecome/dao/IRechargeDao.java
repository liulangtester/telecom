package com.telecome.dao;

import java.sql.SQLException;

import com.telecome.po.User;

public interface IRechargeDao {

	int addRecharge(String money, String account, String rechargeNumber, User user) throws SQLException;

}
