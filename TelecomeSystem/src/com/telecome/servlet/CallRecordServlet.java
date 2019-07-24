package com.telecome.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.telecome.po.User;
import com.telecome.service.ICallRecordService;
import com.telecome.service.impl.CallRecordServiceImpl;
import com.telecome.utils.PageBean;

/**
 * Servlet implementation class CallRecordServlet
 */
@WebServlet("/callRecordServlet")
public class CallRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ICallRecordService callRecordService = new CallRecordServiceImpl();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CallRecordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 查询通话记录（包括模糊查询）
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
				pageBean = callRecordService.getTotalNotice(pageBean,user);
				//获取分页查询数据,并把获取到的数据集合放入pageBean
				pageBean = callRecordService.query(pageBean,user);
				request.setAttribute("notices", pageBean);
				request.setAttribute("bill", pageBean.getT());
				request.getRequestDispatcher("/WEB-INF/view/user_call_record.jsp").forward(request, response);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
						
			
		}else {
			request.setAttribute("msg", "操作失败，请先登录！");
			request.getRequestDispatcher("/userlogin.jsp").forward(request, response);
		}
		
	}
	
	

}
