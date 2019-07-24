package com.telecome.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.telecome.po.RechargeRecord;
import com.telecome.po.User;
import com.telecome.service.IRechargeRecordService;
import com.telecome.service.impl.RechargeRecordServiceImpl;
import com.telecome.utils.PageBean;

/**
 * Servlet implementation class RechargeRecordServlet
 */
@WebServlet("/rechargeRecordServlet")
public class RechargeRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IRechargeRecordService rechargeRecordService = new RechargeRecordServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RechargeRecordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 查询充值记录（包括模糊查询）
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("search");
		String thisPage = request.getParameter("thisPage");
		int pageNum = 1;
		if(!"".equals(thisPage) && null != thisPage) {
			//如果传了thisPage参数过来，把它转换成int类型(分页注释),如果没有则为第一区间的页数
			pageNum = Integer.parseInt(thisPage);
		}
		PageBean pageBean = new PageBean();
		pageBean.setPageNum(pageNum);
		pageBean.setSearch(search);
		pageBean.setPageSize(5);//每页显示数
		
		User user = (User) request.getSession().getAttribute("user");
		if(user != null) {
			try {
				//获取总条数并把总条数放入pageBean
				pageBean = rechargeRecordService.getTotalNotice(pageBean,user);
				//获取分页查询数据,并把获取到的数据集合放入pageBean
				pageBean = rechargeRecordService.query(pageBean,user);
				request.setAttribute("notices", pageBean);
				request.setAttribute("rechargeRecord", pageBean.getT());
				request.getRequestDispatcher("/WEB-INF/view/user_recharge_record.jsp").forward(request, response);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
						
			
		}else {
			request.setAttribute("msg", "操作失败，请先登录！");
			request.getRequestDispatcher("/userlogin.jsp").forward(request, response);
		}	
	}
	
	
}


