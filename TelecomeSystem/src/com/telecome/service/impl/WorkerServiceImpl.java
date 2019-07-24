package com.telecome.service.impl;

import java.sql.SQLException;

import com.telecome.dao.IWorkerDao;
import com.telecome.dao.impl.WorkerDaoImpl;
import com.telecome.po.User;
import com.telecome.po.Worker;
import com.telecome.service.IWorkerService;

public class WorkerServiceImpl implements IWorkerService {
	private IWorkerDao workerDao = new WorkerDaoImpl();
	@Override
	public Worker getWorker(String username, String password) throws SQLException {
		return workerDao.getWorker(username,password);
	}

}
