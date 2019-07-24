package com.telecome.service;

import java.sql.SQLException;

import com.telecome.po.User;
import com.telecome.po.Worker;

public interface IWorkerService {
/**
 * 通过用户名和密码匹配获得worker 用于登录
 * @param username
 * @param password
 * @return
 * @throws SQLException
 */
	Worker getWorker(String username, String password) throws SQLException;

}
