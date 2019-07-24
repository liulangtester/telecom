package com.telecome.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.telecome.dao.IIndustryDao;
import com.telecome.po.CustomerType;
import com.telecome.po.Industry;
import com.telecome.utils.DBUtils;

public class IndustryDaoImpl implements IIndustryDao {

	@Override
	public List getIndustryType() throws SQLException {
		List list = new ArrayList();
		Industry type = null;
		Connection con = DBUtils.getConnection();
		String sql = null;
			sql = "select * from industry";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				type = new Industry();
				type.setIndustry(rs.getString("industry"));
				list.add(type);
			}
			DBUtils.close(con, pst, rs);
	
		return list;
	}

}
