package com.telecome.dao;

import java.sql.SQLException;
import java.util.List;

import com.telecome.po.Bill;
import com.telecome.po.User;
import com.telecome.utils.PageBean;

public interface ICallRecordDao {

//	List getBill(User user) throws SQLException;
//
//	List getCallRecordByPart(String content)throws SQLException;

	PageBean getTotalNotice(PageBean pageBean,User user)throws SQLException;

	PageBean query(PageBean pageBean,User user)throws SQLException;
}
