package com.telecome.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.telecome.dao.ICallRecordDao;
import com.telecome.po.Bill;
import com.telecome.po.BusinessCustomer;
import com.telecome.po.RechargeRecord;
import com.telecome.po.User;
import com.telecome.utils.DBUtils;
import com.telecome.utils.PageBean;

public class CallRecordDaoImpl implements ICallRecordDao {

//	@Override
//	public List getBill(User user) throws SQLException {
//		List<Bill> list = new ArrayList<Bill>();
//		Bill bill = null;
//		Connection con = DBUtils.getConnection();
//		
//		PreparedStatement pst = con.prepareStatement("select * from bill where callNumber=?");
//		pst.setString(1, user.getUser_phone());
//		ResultSet rs = pst.executeQuery();
//		while(rs.next()) {
//			bill = new Bill();
//			bill.setBeCalledNumber(rs.getString("beCalledNumber"));
//			bill.setCallNumber(rs.getString("callNumber"));
//			bill.setCost(rs.getFloat("cost"));
//			bill.setDate(rs.getTimestamp("date"));
//			bill.setId(rs.getString("id"));
//			bill.setTime(rs.getString("time"));
//			list.add(bill);
//		}
//		DBUtils.close(con, pst, rs);
//		
//		return list;
//	}

//	@Override
//	public List getCallRecordByPart(String content) throws SQLException {
//		List<Bill> list = new ArrayList<Bill>();
//		Bill bill = null;
//		Connection con = DBUtils.getConnection();
//		
//		PreparedStatement pst = con.prepareStatement(
//				"SELECT * FROM bill WHERE id LIKE ? OR callNumber LIKE ? OR beCalledNumber LIKE ? OR time LIKE ? OR cost LIKE ? OR date LIKE ?");		
//		pst.setString(1, "%"+content+"%");
//		pst.setString(2, "%"+content+"%");
//		pst.setString(3, "%"+content+"%");
//		pst.setString(4, "%"+content+"%");
//		pst.setString(5, "%"+content+"%");
//		pst.setString(6, "%"+content+"%");
//		ResultSet rs = pst.executeQuery();
//		while(rs.next()) {
//			bill = new Bill();
//			bill.setId(rs.getString("id"));
//			bill.setCallNumber(rs.getString("callNumber"));
//			bill.setBeCalledNumber(rs.getString("beCalledNumber"));
//			bill.setTime(rs.getString("time"));
//			bill.setCost(rs.getFloat("cost"));
//			bill.setDate(rs.getTimestamp("date"));
//			list.add(bill);
//			
//		}
//		DBUtils.close(con, pst, rs);
//		return list;
//	}

	@Override
	public PageBean getTotalNotice(PageBean pageBean,User user) throws SQLException {
		Connection con = DBUtils.getConnection();
		int count = 0;
		String sql = "";
		if(!"".equals(pageBean.getSearch()) && null != pageBean.getSearch()) {
			sql = "SELECT count(1) FROM bill WHERE id LIKE ? OR callNumber LIKE ? OR beCalledNumber LIKE ? OR time LIKE ? OR cost LIKE ? OR date LIKE ? and callNumber=?";
		}else {
			sql = "select count(1) from bill where callNumber=?";
		}
		PreparedStatement pst = con.prepareStatement(sql);
		if(!"".equals(pageBean.getSearch()) && null != pageBean.getSearch()) {
			pst.setString(1, "%"+pageBean.getSearch()+"%");
			pst.setString(2, "%"+pageBean.getSearch()+"%");
			pst.setString(3, "%"+pageBean.getSearch()+"%");
			pst.setString(4, "%"+pageBean.getSearch()+"%");
			pst.setString(5, "%"+pageBean.getSearch()+"%");
			pst.setString(6, "%"+pageBean.getSearch()+"%");
			pst.setString(7, user.getUser_phone());
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
	public PageBean query(PageBean pageBean,User user) throws SQLException {
		Connection con = DBUtils.getConnection();
		Bill bill = null;
		String sql = "";
		if(!"".equals(pageBean.getSearch()) && null != pageBean.getSearch()) {
			sql ="SELECT * FROM bill WHERE id LIKE ? "
					+ "OR callNumber LIKE ? OR beCalledNumber LIKE ? "
					+ "OR time LIKE ? OR cost LIKE ? OR date LIKE ?  and callNumber=? limit ? , ?";
		}else {
			sql = "select * from bill  where callNumber=? limit ? , ?";
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
			pst.setString(7, user.getUser_phone());
			pst.setInt(8, (pageBean.getPageNum()-1)*pageBean.getPageSize());
			pst.setInt(9, pageBean.getPageSize());
			
			
		}else {
			pst.setString(1, user.getUser_phone());
			pst.setInt(2, (pageBean.getPageNum()-1)*pageBean.getPageSize());
			pst.setInt(3, pageBean.getPageSize());
			
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
