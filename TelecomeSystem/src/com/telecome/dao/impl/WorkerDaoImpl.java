package com.telecome.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.telecome.dao.IWorkerDao;
import com.telecome.po.Worker;
import com.telecome.utils.DBUtils;
import com.telecome.utils.MD5Utils;

public class WorkerDaoImpl implements IWorkerDao {

	@Override
	public Worker getWorker(String username, String password)throws SQLException {
		Worker worker = null;
		Connection con = DBUtils.getConnection();
		String sql = null;
		//查员工表
		sql = "select * from worker where worker_username=? and worker_password=?";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, username);
		MD5Utils md5 = new MD5Utils();
		password = md5.getMD5(password);//加密
		pst.setString(2, password);
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			worker = new Worker();
			worker.setWorker_id(rs.getString("worker_id"));
			worker.setWorker_username(rs.getString("worker_username"));
			worker.setWorker_password(rs.getString("worker_password"));
			worker.setWorker_password(rs.getString("worker_name"));
			worker.setWorker_password(rs.getString("worker_sex"));
			worker.setPost_id(rs.getInt("post_id"));
			worker.setState(rs.getInt("state"));
		}
		DBUtils.close(con, pst, rs);
		return worker;
	}

}
