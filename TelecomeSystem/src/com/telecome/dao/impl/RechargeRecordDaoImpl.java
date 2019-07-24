package com.telecome.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.telecome.dao.IRechargeRecordDao;
import com.telecome.po.Bill;
import com.telecome.po.RechargeRecord;
import com.telecome.po.User;
import com.telecome.utils.DBUtils;
import com.telecome.utils.PageBean;

public class RechargeRecordDaoImpl implements IRechargeRecordDao {

	@Override
	public PageBean getTotalNotice(PageBean pageBean, User user) throws SQLException {
		Connection con = DBUtils.getConnection();
		int count = 0;
		String sql = "";
		if(!"".equals(pageBean.getSearch()) && null != pageBean.getSearch()) {
			sql = "SELECT count(1) FROM recharge_record WHERE id LIKE ? OR phone LIKE ? OR account LIKE ? OR time LIKE ? OR money LIKE ?  and phone=?";
		}else {
			sql = "select count(1) from recharge_record where phone=?";
		}
		PreparedStatement pst = con.prepareStatement(sql);
		if(!"".equals(pageBean.getSearch()) && null != pageBean.getSearch()) {
			pst.setString(1, "%"+pageBean.getSearch()+"%");
			pst.setString(2, "%"+pageBean.getSearch()+"%");
			pst.setString(3, "%"+pageBean.getSearch()+"%");
			pst.setString(4, "%"+pageBean.getSearch()+"%");
			pst.setString(5, "%"+pageBean.getSearch()+"%");
			pst.setString(6, user.getUser_phone());
		}else {
			pst.setString(1, user.getUser_phone());
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
	public PageBean query(PageBean pageBean, User user) throws SQLException {
		Connection con = DBUtils.getConnection();
		RechargeRecord rechargeRecord = null;
		String sql = "";
		if(!"".equals(pageBean.getSearch()) && null != pageBean.getSearch()) {
			sql ="SELECT * FROM recharge_record WHERE id LIKE ? "
					+ "OR phone LIKE ? OR account LIKE ? "
					+ "OR time LIKE ? OR money LIKE ?  and phone=? limit ? , ?";
		}else {
			sql = "select * from recharge_record  where phone=? limit ? , ?";
		}
		PreparedStatement pst = con.prepareStatement(sql);
		if(!"".equals(pageBean.getSearch()) && null != pageBean.getSearch()) {
			pst.setString(1, "%"+pageBean.getSearch()+"%");
			pst.setString(2, "%"+pageBean.getSearch()+"%");
			pst.setString(3, "%"+pageBean.getSearch()+"%");
			pst.setString(4, "%"+pageBean.getSearch()+"%");
			pst.setString(5, "%"+pageBean.getSearch()+"%");
			
			pst.setString(6, user.getUser_phone());
			
			pst.setInt(7, (pageBean.getPageNum()-1)*pageBean.getPageSize());
			pst.setInt(8, pageBean.getPageSize());
			
			
		}else {
			pst.setString(1, user.getUser_phone());
			pst.setInt(2, (pageBean.getPageNum()-1)*pageBean.getPageSize());
			pst.setInt(3, pageBean.getPageSize());
			
		}
		
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			rechargeRecord = new RechargeRecord();
			rechargeRecord.setId(rs.getString("id"));
			rechargeRecord.setPhone(rs.getString("phone"));
			rechargeRecord.setAccount(rs.getString("account"));
			rechargeRecord.setMoney(rs.getFloat("money"));
			rechargeRecord.setTime(rs.getTimestamp("time"));
			pageBean.getT().add(rechargeRecord);
		}
		DBUtils.close(con, pst, rs);
	
	return pageBean;
	}

	@Override
	public PageBean getTotalNotice(PageBean pageBean) throws SQLException {
		Connection con = DBUtils.getConnection();
		int count = 0;
		String sql = "";
		if(!"".equals(pageBean.getSearch()) && null != pageBean.getSearch()) {
			sql = "SELECT count(1) FROM recharge_record WHERE id LIKE ? OR phone LIKE ? OR account LIKE ? OR time LIKE ? OR money LIKE ? ";
		}else {
			sql = "select count(1) from recharge_record ";
		}
		PreparedStatement pst = con.prepareStatement(sql);
		if(!"".equals(pageBean.getSearch()) && null != pageBean.getSearch()) {
			pst.setString(1, "%"+pageBean.getSearch()+"%");
			pst.setString(2, "%"+pageBean.getSearch()+"%");
			pst.setString(3, "%"+pageBean.getSearch()+"%");
			pst.setString(4, "%"+pageBean.getSearch()+"%");
			pst.setString(5, "%"+pageBean.getSearch()+"%");
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
		RechargeRecord rechargeRecord = null;
		String sql = "";
		if(!"".equals(pageBean.getSearch()) && null != pageBean.getSearch()) {
			sql ="SELECT * FROM recharge_record WHERE id LIKE ? "
					+ "OR phone LIKE ? OR account LIKE ? "
					+ "OR time LIKE ? OR money LIKE ?  limit ? , ?";
		}else {
			sql = "select * from recharge_record limit ? , ?";
		}
		PreparedStatement pst = con.prepareStatement(sql);
		if(!"".equals(pageBean.getSearch()) && null != pageBean.getSearch()) {
			pst.setString(1, "%"+pageBean.getSearch()+"%");
			pst.setString(2, "%"+pageBean.getSearch()+"%");
			pst.setString(3, "%"+pageBean.getSearch()+"%");
			pst.setString(4, "%"+pageBean.getSearch()+"%");
			pst.setString(5, "%"+pageBean.getSearch()+"%");
			
			pst.setInt(6, (pageBean.getPageNum()-1)*pageBean.getPageSize());
			pst.setInt(7, pageBean.getPageSize());
			
			
		}else {
			pst.setInt(1, (pageBean.getPageNum()-1)*pageBean.getPageSize());
			pst.setInt(2, pageBean.getPageSize());
			
		}
		
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			rechargeRecord = new RechargeRecord();
			rechargeRecord.setId(rs.getString("id"));
			rechargeRecord.setPhone(rs.getString("phone"));
			rechargeRecord.setAccount(rs.getString("account"));
			rechargeRecord.setMoney(rs.getFloat("money"));
			rechargeRecord.setTime(rs.getTimestamp("time"));
			pageBean.getT().add(rechargeRecord);
		}
		DBUtils.close(con, pst, rs);
	
	return pageBean;
	}

}
