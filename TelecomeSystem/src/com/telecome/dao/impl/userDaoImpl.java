package com.telecome.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.telecome.utils.*;
import com.telecome.dao.IUserDao;
import com.telecome.po.Account;
import com.telecome.po.User;

public class userDaoImpl implements IUserDao {

	@Override
	public User getUser(String username, String password) throws SQLException {
		User user = null;
		Connection con = DBUtils.getConnection();
		String sql = null;
			//查用户表
			sql = "select * from user where user_phone=? and user_password=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, username);
			MD5Utils md5 = new MD5Utils();
			password = md5.getMD5(password);//加密
			pst.setString(2, password);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				user = new User();
				user.setUser_phone(rs.getString("user_phone"));
				user.setUser_address(rs.getString("user_address"));
				user.setUser_bank(rs.getString("user_bank"));
				user.setUser_operator(rs.getString("user_operator"));
				user.setUser_password(password);
				user.setUser_money(rs.getFloat("user_money"));
			}
			DBUtils.close(con, pst, rs);
	
		return user;
	}

	@Override
	public User getUser(String username) throws SQLException {
		User user = null;
		Connection con = DBUtils.getConnection();
		String sql = null;
			//查用户表
			sql = "select * from user where user_phone=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				user = new User();
				user.setUser_phone(rs.getString("user_phone"));
				user.setUser_address(rs.getString("user_address"));
				user.setUser_bank(rs.getString("user_bank"));
				user.setUser_operator(rs.getString("user_operator"));
				user.setUser_password(rs.getString("user_password"));
				user.setUser_money(rs.getFloat("user_money"));
			}
			DBUtils.close(con, pst, rs);
	
		return user;
	}

	@Override
	public int addToReserve(String phone, String address, String account, String password, String remain_money,
			String type) throws SQLException {
		int count = 0;		

		Connection con = DBUtils.getConnection();

		PreparedStatement pst = con.prepareStatement("insert into user(user_phone, user_address, user_bank, user_password, user_money, user_type) value(?,?,?,?,?,?)");	
		pst.setString(1, phone);
		pst.setString(2, address);
		pst.setString(3, account);
		MD5Utils md5 = new MD5Utils();
		password = md5.getMD5(password);
		pst.setString(4, password);
		pst.setString(5, remain_money);
		pst.setString(6, type);
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
			sql = "SELECT count(1) FROM user WHERE user_phone LIKE ? OR user_address LIKE ? OR user_bank LIKE ? OR user_money LIKE ? OR user_id LIKE ? OR user_type LIKE ? ";
		}else {
			sql = "select count(1) from user";
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
		User user = null;
		String sql = "";
		if(!"".equals(pageBean.getSearch()) && null != pageBean.getSearch()) {
			sql ="SELECT * FROM user WHERE user_phone LIKE ? OR user_address LIKE ? OR user_bank LIKE ? OR user_money LIKE ? OR user_id LIKE ? OR user_type LIKE ? limit ? , ?";
		}else {
			sql = "select * from user limit ? , ?";
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
			user = new User();
			user.setId(rs.getInt("user_id"));
			user.setUser_phone(rs.getString("user_phone"));
			user.setUser_password(rs.getString("user_password"));
			user.setUser_address(rs.getString("user_address"));
			user.setUser_bank(rs.getString("user_bank"));
			user.setUser_money(rs.getFloat("user_money"));
			user.setUser_type(rs.getString("user_type"));
			pageBean.getT().add(user);
		}
		DBUtils.close(con, pst, rs);
	
	return pageBean;
	}

	@Override
	public int delete(String id) throws SQLException {
		int count = 0;
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = con.prepareStatement("delete from user where user_id=?");
		pst.setInt(1, Integer.parseInt(id));
		count = pst.executeUpdate();
		DBUtils.close(con, pst, null);
		return count;
	}

	@Override
	public User queryById(String id) throws SQLException {
		User user = null;
		Connection con = DBUtils.getConnection();
		
		PreparedStatement pst = con.prepareStatement(
				"SELECT * FROM user WHERE user_id=?");		
		pst.setString(1, id);

		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			user = new User();
			user.setId(rs.getInt("user_id"));
			user.setUser_phone(rs.getString("user_phone"));
			user.setUser_password(rs.getString("user_password"));
			user.setUser_address(rs.getString("user_address"));
			user.setUser_bank(rs.getString("user_bank"));
			user.setUser_money(rs.getFloat("user_money"));
			user.setUser_type(rs.getString("user_type"));
					
		}
		DBUtils.close(con, pst, rs);
		return user;
	}

	@Override
	public int updateToReserve(String phone, String address, String account, String remain_money, String type, String id)
			throws SQLException {
		int count = 0;		

		Connection con = DBUtils.getConnection();
		con.setAutoCommit(false);
		String sql = " update user set user_phone=? , user_address=? , user_bank=?"
				+ " , user_money=? , user_type=?  where user_id=? ";
		PreparedStatement pst = con.prepareStatement(sql);	
	
		pst.setString(1, phone);
		pst.setString(2, address);
		pst.setString(3, account);
		pst.setString(4, remain_money);
		pst.setString(5, type);
		pst.setString(6, id);
		
		count = pst.executeUpdate();
		con.setAutoCommit(true);
		DBUtils.close(con, pst, null);

		return count;
	}

	

}
