package com.telecome.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.telecome.dao.ISpecialCustomerDao;
import com.telecome.po.BusinessCustomer;
import com.telecome.po.SpecialCustomer;
import com.telecome.utils.DBUtils;
import com.telecome.utils.PageBean;

public class SpecialCustomerDaoImpl implements ISpecialCustomerDao {


	@Override
	public int updateToReserve(String phone, String name, String customerType, String address, String id,
			String manager) throws SQLException {	
		int count = 0;		

		Connection con = DBUtils.getConnection();

		PreparedStatement pst = con.prepareStatement("update special_customer set user_phone=?, user_name=?, address=?, manager=? where id=? ");	
		pst.setString(1, phone);
		pst.setString(2, name);
		pst.setString(3, address);
		pst.setString(4, manager);
		pst.setString(5, id);
		count = pst.executeUpdate();		

		DBUtils.close(con, pst, null);
		return count;
	}

	@Override
	public int addToReserve(String phone, String name, String customerType, String address, String manager)
			throws SQLException {
		int count = 0;		

		Connection con = DBUtils.getConnection();

		PreparedStatement pst = con.prepareStatement("insert into special_customer(user_phone,user_name,address,manager) value(?,?,?,?)");	
		pst.setString(1, phone);
		pst.setString(2, name);
		pst.setString(3, address);
		pst.setString(4, manager);
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
			sql = "SELECT count(1) FROM special_customer WHERE user_phone LIKE ? OR user_name LIKE ? OR user_type LIKE ? OR address LIKE ? OR manager LIKE ? OR id LIKE ?";
		}else {
			sql = "select count(1) from special_customer";
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
		SpecialCustomer specialCustomer = null;
		String sql = "";
		if(!"".equals(pageBean.getSearch()) && null != pageBean.getSearch()) {
			sql ="SELECT * FROM special_customer WHERE user_phone LIKE ? "
					+ "OR user_name LIKE ? OR user_type LIKE ? "
					+ "OR address LIKE ? OR manager LIKE ? OR id LIKE ? limit ? , ?";
		}else {
			sql = "select * from special_customer limit ? , ?";
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
			specialCustomer = new SpecialCustomer();
			specialCustomer.setPhone(rs.getString("user_phone"));
			specialCustomer.setName(rs.getString("user_name"));
			specialCustomer.setType(rs.getString("user_type"));
			specialCustomer.setAddress(rs.getString("address"));
			specialCustomer.setManager(rs.getString("manager"));
			specialCustomer.setId(rs.getInt("id"));
			
			pageBean.getT().add(specialCustomer);
		}
		DBUtils.close(con, pst, rs);
	
	return pageBean;
	}

	@Override
	public int delete(String id) throws SQLException {
		int count = 0;
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = con.prepareStatement("delete from special_customer where id=?");
		pst.setInt(1, Integer.parseInt(id));
		count = pst.executeUpdate();
		DBUtils.close(con, pst, null);
		return count;
	}

	@Override
	public SpecialCustomer queryById(String id) throws SQLException {
		SpecialCustomer specialCustomer = null;
		Connection con = DBUtils.getConnection();
		
		PreparedStatement pst = con.prepareStatement(
				"SELECT * FROM special_customer WHERE id=?");		
		pst.setString(1, id);

		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			specialCustomer = new SpecialCustomer();
			specialCustomer.setPhone(rs.getString("user_phone"));
			specialCustomer.setName(rs.getString("user_name"));
			specialCustomer.setType(rs.getString("user_type"));
			specialCustomer.setAddress(rs.getString("address"));
			specialCustomer.setManager(rs.getString("manager"));
			specialCustomer.setId(rs.getInt("id"));
			
		}
		DBUtils.close(con, pst, rs);
		return specialCustomer;
	}

}
