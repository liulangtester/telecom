package com.telecome.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.telecome.dao.IAccountDao;
import com.telecome.po.Account;
import com.telecome.utils.DBUtils;
import com.telecome.utils.PageBean;

public class AccountDaoImpl implements IAccountDao {

	@Override
	public int addToReserve(String phone, String name, String account, String bank, String s_bank, String remain_money,
			String total_cost, String month_cost) throws SQLException {
		int count = 0;		

		Connection con = DBUtils.getConnection();

		PreparedStatement pst = con.prepareStatement("insert into account(user_phone,user_name,bank_account,open_bank,sub_bank,remainder_money,cost_money,month_money) value(?,?,?,?,?,?,?,?)");	
		pst.setString(1, phone);
		pst.setString(2, name);
		pst.setString(3, account);
		pst.setString(4, bank);
		pst.setString(5, s_bank);
		pst.setString(6, remain_money);
		pst.setString(7, total_cost);
		pst.setString(8, month_cost);
		count = pst.executeUpdate();		

		DBUtils.close(con, pst, null);
		return count;
	}

	@Override
	public int updateToReserve(String phone, String name, String account, String bank, String s_bank,
			String remain_money, String total_cost, String month_cost, String id) throws SQLException {
		int count = 0;		

		Connection con = DBUtils.getConnection();
		con.setAutoCommit(false);
		String sql = " update account set user_phone=? , user_name=? , bank_account=?"
				+ " , open_bank=? , sub_bank=? , remainder_money=? , cost_money=? , month_money=? where id=? ";
		PreparedStatement pst = con.prepareStatement(sql);	
	
		pst.setString(1, phone);
		pst.setString(2, name);
		pst.setString(3, account);
		pst.setString(4, bank);
		pst.setString(5, s_bank);
		pst.setString(6, remain_money);
		pst.setString(7, total_cost);
		pst.setString(8, month_cost);
		pst.setString(9, id);
		
		count = pst.executeUpdate();
		con.setAutoCommit(true);
		DBUtils.close(con, pst, null);

		return count;
	}

	@Override
	public PageBean getTotalNotice(PageBean pageBean) throws SQLException {
		Connection con = DBUtils.getConnection();
		int count = 0;
		String sql = "";
		if(!"".equals(pageBean.getSearch()) && null != pageBean.getSearch()) {
			sql = "SELECT count(1) FROM account WHERE user_phone LIKE ? OR user_name LIKE ? OR bank_account LIKE ? OR open_bank LIKE ? OR sub_bank LIKE ? OR remainder_money LIKE ? OR cost_money LIKE ? OR month_money LIKE ? OR id LIKE ?";
		}else {
			sql = "select count(1) from account";
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
			pst.setString(9, "%"+pageBean.getSearch()+"%");
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
		Account account = null;
		String sql = "";
		if(!"".equals(pageBean.getSearch()) && null != pageBean.getSearch()) {
			sql ="SELECT * FROM account WHERE user_phone LIKE ? OR user_name LIKE ? OR bank_account LIKE ? OR open_bank LIKE ? OR sub_bank LIKE ? OR remainder_money LIKE ? OR cost_money LIKE ? OR month_money LIKE ? OR id LIKE ? limit ? , ?";
		}else {
			sql = "select * from account limit ? , ?";
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
			pst.setString(9, "%"+pageBean.getSearch()+"%");
//			System.out.println((pageNum-1)*pageSize);
			pst.setInt(10, (pageBean.getPageNum()-1)*pageBean.getPageSize());
			pst.setInt(11, pageBean.getPageSize());
		}else {
			pst.setInt(1, (pageBean.getPageNum()-1)*pageBean.getPageSize());
			pst.setInt(2, pageBean.getPageSize());
		}
		
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			account = new Account();
			account.setUser_phone(rs.getString("user_phone"));
			account.setUser_name(rs.getString("user_name"));
			account.setBank_account(rs.getString("bank_account"));
			account.setOpen_bank(rs.getString("open_bank"));
			account.setSub_bank(rs.getString("sub_bank"));
			account.setRemainder_money(rs.getFloat("remainder_money"));
			account.setCost_money(rs.getFloat("cost_money"));
			account.setMonth_money(rs.getFloat("month_money"));
			account.setId(rs.getInt("id"));
			pageBean.getT().add(account);
		}
		DBUtils.close(con, pst, rs);
	
	return pageBean;
	}

	@Override
	public Account queryById(String id) throws SQLException {
		Account account = null;
		Connection con = DBUtils.getConnection();
		
		PreparedStatement pst = con.prepareStatement(
				"SELECT * FROM account WHERE id=?");		
		pst.setString(1, id);

		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			account = new Account();
			account.setUser_phone(rs.getString("user_phone"));
			account.setUser_name(rs.getString("user_name"));
			account.setBank_account(rs.getString("bank_account"));
			account.setOpen_bank(rs.getString("open_bank"));
			account.setSub_bank(rs.getString("sub_bank"));
			account.setRemainder_money(rs.getFloat("remainder_money"));
			account.setCost_money(rs.getFloat("cost_money"));
			account.setMonth_money(rs.getFloat("month_money"));
			account.setId(rs.getInt("id"));
			
		}
		DBUtils.close(con, pst, rs);
		return account;
	}

	@Override
	public int delete(String id) throws SQLException {
		int count = 0;
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = con.prepareStatement("delete from account where id=?");
		pst.setInt(1, Integer.parseInt(id));
		count = pst.executeUpdate();
		DBUtils.close(con, pst, null);
		return count;
	}

}
