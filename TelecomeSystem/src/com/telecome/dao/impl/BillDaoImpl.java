package com.telecome.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.telecome.dao.IBillDao;
import com.telecome.po.Account;
import com.telecome.po.Bill;
import com.telecome.po.User;
import com.telecome.utils.CalculateCost;
import com.telecome.utils.DBUtils;
import com.telecome.utils.PageBean;

public class BillDaoImpl implements IBillDao {

	@Override
	public int getBill(Bill bill, User user) throws SQLException {
		CalculateCost cal = new CalculateCost();
		//获得产生的话费费用
		bill.setCost(cal.calculate(bill,user));
		
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = con.prepareStatement("insert into bill(id,callNumber,beCalledNumber,time,cost) value(?,?,?,?,?)");
		
		//账单自动生成：当前日期+6位随机数
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String s = sdf.format(d);
		s = s + (int)((Math.random()*9+1)*100000);
		
		pst.setString(1, s);
		pst.setString(2, bill.getCallNumber());
		pst.setString(3, bill.getBeCalledNumber());
		pst.setString(4, bill.getTime());
		pst.setFloat(5, bill.getCost());
		//还有一个通话日期数据库自动生成
		int count0 = pst.executeUpdate();
		
		//更新user表的余额
		pst = con.prepareStatement("update user set user_money=? where user_phone = ?");
		pst.setFloat(1, user.getUser_money()-bill.getCost());
		pst.setString(2, user.getUser_phone());
		int count1 = pst.executeUpdate();
		
		int count = 0;
		if(count0 > 0 && count1 >0) {
			//两条语句都成功了，才算成功
			count = 1;
		}
		
		DBUtils.close(con, pst, null);
		return count;
	}

	@Override
	public PageBean getTotalNotice(PageBean pageBean) throws SQLException {
		Connection con = DBUtils.getConnection();
		int count = 0;
		String sql = "";
		if(!"".equals(pageBean.getSearch()) && null != pageBean.getSearch()) {
			sql = "SELECT count(1) FROM bill WHERE id LIKE ? OR callNumber LIKE ? OR beCalledNumber LIKE ? OR time LIKE ? OR cost LIKE ? OR date LIKE ? ";
		}else {
			sql = "select count(1) from bill";
		}
		PreparedStatement pst = con.prepareStatement(sql);
		if(!"".equals(pageBean.getSearch()) && null != pageBean.getSearch()) {
			pst.setString(1, "%"+pageBean.getSearch()+"%");
			pst.setString(2, "%"+pageBean.getSearch()+"%");
			pst.setString(3, "%"+pageBean.getSearch()+"%");
			pst.setString(4, "%"+pageBean.getSearch()+"%");
			pst.setString(5, "%"+pageBean.getSearch()+"%");
			pst.setString(6, "%"+pageBean.getSearch()+"%");
		}
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			count = rs.getInt(1);//获取第一列的条数
		}
		DBUtils.close(con, pst, rs);
		pageBean.setTotalCount(count);
	return pageBean;
	}

	@Override
	public PageBean query(PageBean pageBean) throws SQLException {
		Connection con = DBUtils.getConnection();
		Bill bill = null;
		String sql = "";
		if(!"".equals(pageBean.getSearch()) && null != pageBean.getSearch()) {
			sql ="SELECT * FROM bill WHERE id LIKE ? OR callNumber LIKE ? OR beCalledNumber LIKE ? OR time LIKE ? OR cost LIKE ? OR date LIKE ? limit ? , ?";
		}else {
			sql = "select * from bill limit ? , ?";
		}
		PreparedStatement pst = con.prepareStatement(sql);
		if(!"".equals(pageBean.getSearch()) && null != pageBean.getSearch()) {
			pst.setString(1, "%"+pageBean.getSearch()+"%");
			pst.setString(2, "%"+pageBean.getSearch()+"%");
			pst.setString(3, "%"+pageBean.getSearch()+"%");
			pst.setString(4, "%"+pageBean.getSearch()+"%");
			pst.setString(5, "%"+pageBean.getSearch()+"%");
			pst.setString(6, "%"+pageBean.getSearch()+"%");
//			System.out.println((pageNum-1)*pageSize);
			pst.setInt(7, (pageBean.getPageNum()-1)*pageBean.getPageSize());
			pst.setInt(8, pageBean.getPageSize());
		}else {
			pst.setInt(1, (pageBean.getPageNum()-1)*pageBean.getPageSize());
			pst.setInt(2, pageBean.getPageSize());
		}
		
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			bill = new Bill();
			bill.setId(rs.getString("id"));
			bill.setCallNumber(rs.getString("callNumber"));
			bill.setBeCalledNumber(rs.getString("beCalledNumber"));
			bill.setTime(rs.getString("time"));
			bill.setCost(rs.getFloat("cost"));
			bill.setDate(rs.getTimestamp("date"));
			pageBean.getT().add(bill);
		}
		DBUtils.close(con, pst, rs);
	
	return pageBean;
	}



}
