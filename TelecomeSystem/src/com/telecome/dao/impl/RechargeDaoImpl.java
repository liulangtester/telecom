package com.telecome.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.telecome.dao.IRechargeDao;
import com.telecome.po.User;

import com.telecome.utils.DBUtils;

public class RechargeDaoImpl implements IRechargeDao {

	@Override
	public int addRecharge(String money, String account, String rechargeNumber, User user) throws SQLException {
		int count = 0;
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = con.prepareStatement("insert into recharge_record(id,phone,account,money) value(?,?,?,?)");
		//充值id号自动生成：当前时间+6位随机数
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		String s = sdf.format(d);
		s = s + (int)((Math.random()*9+1)*100000);
		
		pst.setString(1, s);
		pst.setString(2, rechargeNumber);
		pst.setString(3, account);
		pst.setString(4, money);
		int count0 = pst.executeUpdate();
		
		//更新user表的余额
		pst = con.prepareStatement("update user set user_money=? where user_phone = ?");
		pst.setFloat(1, user.getUser_money()+Integer.parseInt(money));
		pst.setString(2, rechargeNumber);
		int count1 = pst.executeUpdate();
		DBUtils.close(con, pst, null);
		
		if(count0 > 0 && count1 >0) {
			//两条语句都成功了，才算成功
			count = 1;
		}
		return count;
	}

}
