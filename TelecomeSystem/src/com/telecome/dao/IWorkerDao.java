package com.telecome.dao;

import java.sql.SQLException;

import com.telecome.po.Worker;

public interface IWorkerDao {

	Worker getWorker(String username, String password) throws SQLException;

}
