package com.telecome.service;

import java.sql.SQLException;
import java.util.List;

import com.telecome.po.User;
import com.telecome.utils.PageBean;

public interface IRechargeRecordService {

PageBean getTotalNotice(PageBean pageBean, User user)throws SQLException;

PageBean query(PageBean pageBean, User user)throws SQLException;

PageBean getTotalNotice(PageBean pageBean)throws SQLException;

PageBean query(PageBean pageBean)throws SQLException;

}
