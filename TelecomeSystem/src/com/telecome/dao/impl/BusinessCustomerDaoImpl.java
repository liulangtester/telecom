package com.telecome.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.telecome.dao.IBusinessCustomerDao;
import com.telecome.po.BusinessCustomer;
import com.telecome.po.RechargeRecord;
import com.telecome.utils.DBUtils;
import com.telecome.utils.PageBean;

public class BusinessCustomerDaoImpl implements IBusinessCustomerDao {

	@Override
	public int addToReserve(String phone, String name, String customerType, String address, String manager,
			String industry) throws SQLException {
		int count = 0;		

		Connection con = DBUtils.getConnection();

		PreparedStatement pst = con.prepareStatement("insert into business_customer(user_phone,user_name,address,manager,industry) value(?,?,?,?,?)");	
		pst.setString(1, phone);
		pst.setString(2, name);
		pst.setString(3, address);
		pst.setString(4, manager);
		pst.setString(5, industry);
		count = pst.executeUpdate();		

		DBUtils.close(con, pst, null);
		return count;
	}

	@Override
	public PageBean query(PageBean pageBean) throws SQLException {
		Connection con = DBUtils.getConnection();
		BusinessCustomer businessCustomer = null;
		String sql = "";
		if(!"".equals(pageBean.getSearch()) && null != pageBean.getSearch()) {
			sql ="SELECT * FROM business_customer WHERE user_phone LIKE ? "
					+ "OR user_name LIKE ? OR user_type LIKE ? "
					+ "OR address LIKE ? OR manager LIKE ? OR industry LIKE ? OR id LIKE ? limit ? , ?";
		}else {
			sql = "select * from business_customer limit ? , ?";
		}
		PreparedStatement pst = con.prepareStatement(sql);
		if(!"".equals(pageBean.getSearch()) && null != pageBean.getSearch()) {
			pst.setString(1, "%"+pageBean.getSearch()+"%");
			pst.setString(2, "%"+pageBean.getSearch()+"%");
			pst.setString(3, "%"+pageBean.getSearch()+"%");
			pst.setString(4, "%"+pageBean.getSearch()+"%");
			pst.setString(5, "%"+pageBean.getSearch()+"%");
			pst.setString(6, "%"+pageBean.getSearch()+"%");
			pst.setString(7, "%"+pageBean.getSearch()+"%");
//			System.out.println((pageNum-1)*pageSize);
			pst.setInt(8, (pageBean.getPageNum()-1)*pageBean.getPageSize());
			pst.setInt(9, pageBean.getPageSize());
		}else {
			pst.setInt(1, (pageBean.getPageNum()-1)*pageBean.getPageSize());
			pst.setInt(2, pageBean.getPageSize());
		}
		
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			businessCustomer = new BusinessCustomer();
			businessCustomer.setPhone(rs.getString("user_phone"));
			businessCustomer.setName(rs.getString("user_name"));
			businessCustomer.setType(rs.getString("user_type"));
			businessCustomer.setAddress(rs.getString("address"));
			businessCustomer.setManager(rs.getString("manager"));
			businessCustomer.setIndustry(rs.getString("industry"));
			businessCustomer.setId(rs.getInt("id"));
			
			pageBean.getT().add(businessCustomer);
		}
		DBUtils.close(con, pst, rs);
	
	return pageBean;
	/*
		List<BusinessCustomer> list = new ArrayList<BusinessCustomer>();
		BusinessCustomer businessCustomer = null;
		Connection con = DBUtils.getConnection();
		
		PreparedStatement pst = con.prepareStatement("select * from business_customer");
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			businessCustomer = new BusinessCustomer();
			businessCustomer.setPhone(rs.getString("user_phone"));
			businessCustomer.setName(rs.getString("user_name"));
			businessCustomer.setType(rs.getString("user_type"));
			businessCustomer.setAddress(rs.getString("address"));
			businessCustomer.setManager(rs.getString("manager"));
			businessCustomer.setIndustry(rs.getString("industry"));
			businessCustomer.setId(rs.getInt("id"));
			list.add(businessCustomer);
			
		}
		DBUtils.close(con, pst, rs);
		return pageBean;
		*/
	}

	@Override
	public List queryByPart(String content) throws SQLException {
		List<BusinessCustomer> list = new ArrayList<BusinessCustomer>();
		BusinessCustomer businessCustomer = null;
		Connection con = DBUtils.getConnection();
		
		PreparedStatement pst = con.prepareStatement(
				"SELECT * FROM business_customer WHERE user_phone LIKE ? OR user_name LIKE ? OR user_type LIKE ? OR address LIKE ? OR manager LIKE ? OR industry LIKE ? OR id LIKE ?");		
		pst.setString(1, "%"+content+"%");
		pst.setString(2, "%"+content+"%");
		pst.setString(3, "%"+content+"%");
		pst.setString(4, "%"+content+"%");
		pst.setString(5, "%"+content+"%");
		pst.setString(6, "%"+content+"%");
		pst.setString(7, "%"+content+"%");
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			businessCustomer = new BusinessCustomer();
			businessCustomer.setPhone(rs.getString("user_phone"));
			businessCustomer.setName(rs.getString("user_name"));
			businessCustomer.setType(rs.getString("user_type"));
			businessCustomer.setAddress(rs.getString("address"));
			businessCustomer.setManager(rs.getString("manager"));
			businessCustomer.setIndustry(rs.getString("industry"));
			businessCustomer.setId(rs.getInt("id"));
			list.add(businessCustomer);
			
		}
		DBUtils.close(con, pst, rs);
		return list;
	}

	@Override
	public int delete(String id) throws SQLException {
		int count = 0;
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = con.prepareStatement("delete from business_customer where id=?");
		pst.setInt(1, Integer.parseInt(id));
		count = pst.executeUpdate();
		DBUtils.close(con, pst, null);
		return count;
	}

	@Override
	public BusinessCustomer queryById(String id) throws SQLException {
		BusinessCustomer businessCustomer = null;
		Connection con = DBUtils.getConnection();
		
		PreparedStatement pst = con.prepareStatement(
				"SELECT * FROM business_customer WHERE id=?");		
		pst.setString(1, id);

		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			businessCustomer = new BusinessCustomer();
			businessCustomer.setPhone(rs.getString("user_phone"));
			businessCustomer.setName(rs.getString("user_name"));
			businessCustomer.setType(rs.getString("user_type"));
			businessCustomer.setAddress(rs.getString("address"));
			businessCustomer.setManager(rs.getString("manager"));
			businessCustomer.setIndustry(rs.getString("industry"));
			businessCustomer.setId(rs.getInt("id"));
			
		}
		DBUtils.close(con, pst, rs);
		return businessCustomer;
	}

	@Override
	public int updateToReserve(String phone, String name, String customerType, String address, String manager,
			String industry, String id) throws SQLException {
		int count = 0;		

		Connection con = DBUtils.getConnection();

		PreparedStatement pst = con.prepareStatement("update business_customer set user_phone=?, user_name=?, address=?, manager=?, industry=? where id=? ");	
		pst.setString(1, phone);
		pst.setString(2, name);
		pst.setString(3, address);
		pst.setString(4, manager);
		pst.setString(5, industry);
		pst.setString(6, id);
		count = pst.executeUpdate();		

		DBUtils.close(con, pst, null);
		return count;
	}

	@Override
	public PageBean getTotalNotice(PageBean pageBean) throws SQLException {
		Connection con = DBUtils.getConnection();
		int count = 0;
		String sql = "";
		if(!"".equals(pageBean.getSearch()) && null != pageBean.getSearch()) {
			sql = "SELECT count(1) FROM business_customer WHERE user_phone LIKE ? OR user_name LIKE ? OR user_type LIKE ? OR address LIKE ? OR manager LIKE ? OR industry LIKE ? OR id LIKE ?";
		}else {
			sql = "select count(1) from business_customer";
		}
		PreparedStatement pst = con.prepareStatement(sql);
		if(!"".equals(pageBean.getSearch()) && null != pageBean.getSearch()) {
			pst.setString(1, "%"+pageBean.getSearch()+"%");
			pst.setString(2, "%"+pageBean.getSearch()+"%");
			pst.setString(3, "%"+pageBean.getSearch()+"%");
			pst.setString(4, "%"+pageBean.getSearch()+"%");
			pst.setString(5, "%"+pageBean.getSearch()+"%");
			pst.setString(6, "%"+pageBean.getSearch()+"%");
			pst.setString(7, "%"+pageBean.getSearch()+"%");
		}
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			count = rs.getInt(1);//获取第一列的条数
		}
		DBUtils.close(con, pst, rs);
		pageBean.setTotalCount(count);
	return pageBean;
	}

}
