package com.telecome.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.telecome.dao.ITypeDao;
import com.telecome.po.CustomerType;
import com.telecome.utils.DBUtils;


public class TypeDaoImpl implements ITypeDao {

	@Override
	public List getCustomerType() throws SQLException {
		List list = new ArrayList();
		CustomerType type = null;
		Connection con = DBUtils.getConnection();
		String sql = null;
			sql = "select * from customer_type";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				type = new CustomerType();
				type.setType(rs.getString("customer_type"));
				list.add(type);
			}
			DBUtils.close(con, pst, rs);
	
		return list;
	}

}
