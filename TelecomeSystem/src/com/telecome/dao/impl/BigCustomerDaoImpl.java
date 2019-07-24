package com.telecome.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.telecome.dao.IBigCustomerDao;
import com.telecome.po.BigCustomer;
import com.telecome.po.BusinessCustomer;
import com.telecome.po.PublicCustomer;
import com.telecome.utils.DBUtils;
import com.telecome.utils.PageBean;

public class BigCustomerDaoImpl implements IBigCustomerDao {

	@Override
	public int updateToReserve(String phone, String name, String customerType, String address, String id,
			String manager, String industry, String tc) throws SQLException{
		int count = 0;		
		Connection con = DBUtils.getConnection();

		PreparedStatement pst = con.prepareStatement("update big_customer set user_phone=?, user_name=?, address=?, manager=?, industry=?, TC=? where id=? ");	
		pst.setString(1, phone);
		pst.setString(2, name);
		pst.setString(3, address);
		pst.setString(4, manager);
		pst.setString(5, industry);
		pst.setString(6, tc);
		pst.setString(7, id);
		count = pst.executeUpdate();		

		DBUtils.close(con, pst, null);
		return count;
	}

	@Override
	public int addToReserve(String phone, String name, String customerType, String address, String manager,
			String industry, String tc) throws SQLException {
		int count = 0;		

		Connection con = DBUtils.getConnection();

		PreparedStatement pst = con.prepareStatement("insert into big_customer(user_phone,user_name,address,manager,industry,TC) value(?,?,?,?,?,?)");	
		pst.setString(1, phone);
		pst.setString(2, name);
		pst.setString(3, address);
		pst.setString(4, manager);
		pst.setString(5, industry);
		pst.setString(6, tc);
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
			sql = "SELECT count(1) FROM big_customer WHERE user_phone LIKE ? OR user_name LIKE ? OR user_type LIKE ? OR address LIKE ? OR manager LIKE ? OR industry LIKE ? OR id LIKE ? OR TC LIKE ?";
		}else {
			sql = "select count(1) from big_customer";
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
			pst.setString(8, "%"+pageBean.getSearch()+"%");
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
		BigCustomer bigCustomer = null;
		String sql = "";
		if(!"".equals(pageBean.getSearch()) && null != pageBean.getSearch()) {
			sql ="SELECT * FROM big_customer WHERE user_phone LIKE ? "
					+ "OR user_name LIKE ? OR user_type LIKE ? "
					+ "OR address LIKE ? OR manager LIKE ? OR industry LIKE ? OR id LIKE ? OR TC LIKE ? limit ? , ?";
		}else {
			sql = "select * from big_customer limit ? , ?";
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
			pst.setString(8, "%"+pageBean.getSearch()+"%");
//			System.out.println((pageNum-1)*pageSize);
			pst.setInt(9, (pageBean.getPageNum()-1)*pageBean.getPageSize());
			pst.setInt(10, pageBean.getPageSize());
		}else {
			pst.setInt(1, (pageBean.getPageNum()-1)*pageBean.getPageSize());
			pst.setInt(2, pageBean.getPageSize());
		}
		
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			bigCustomer = new BigCustomer();
			bigCustomer.setPhone(rs.getString("user_phone"));
			bigCustomer.setName(rs.getString("user_name"));
			bigCustomer.setType(rs.getString("user_type"));
			bigCustomer.setAddress(rs.getString("address"));
			bigCustomer.setManager(rs.getString("manager"));
			bigCustomer.setIndustry(rs.getString("industry"));
			bigCustomer.setId(rs.getInt("id"));
			bigCustomer.setTc(rs.getString("TC"));
			
			pageBean.getT().add(bigCustomer);
		}
		DBUtils.close(con, pst, rs);
	
	return pageBean;
	}

	@Override
	public int delete(String id) throws SQLException {
		int count = 0;
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = con.prepareStatement("delete from big_customer where id=?");
		pst.setInt(1, Integer.parseInt(id));
		count = pst.executeUpdate();
		DBUtils.close(con, pst, null);
		return count;
	}

	@Override
	public BigCustomer queryById(String id) throws SQLException {
		BigCustomer bigCustomer = null;
		Connection con = DBUtils.getConnection();
		
		PreparedStatement pst = con.prepareStatement(
				"SELECT * FROM big_customer WHERE id=?");		
		pst.setString(1, id);

		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			bigCustomer = new BigCustomer();
			bigCustomer.setPhone(rs.getString("user_phone"));
			bigCustomer.setName(rs.getString("user_name"));
			bigCustomer.setType(rs.getString("user_type"));
			bigCustomer.setAddress(rs.getString("address"));
			bigCustomer.setManager(rs.getString("manager"));
			bigCustomer.setIndustry(rs.getString("industry"));
			bigCustomer.setId(rs.getInt("id"));
			bigCustomer.setTc(rs.getString("TC"));
			
		}
		DBUtils.close(con, pst, rs);
		return bigCustomer;
	}

}
