package com.telecome.service;

import java.sql.SQLException;

import com.telecome.po.User;
import com.telecome.po.Worker;

public interface IWorkerService {
/**
 * ͨ���û���������ƥ����worker ���ڵ�¼
 * @param username
 * @param password
 * @return
 * @throws SQLException
 */
	Worker getWorker(String username, String password) throws SQLException;

}
