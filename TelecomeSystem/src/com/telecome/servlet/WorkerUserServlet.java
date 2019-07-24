package com.telecome.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.telecome.po.Account;
import com.telecome.po.User;
import com.telecome.po.Worker;
import com.telecome.service.ITypeService;
import com.telecome.service.IUserService;
import com.telecome.service.impl.TypeServiceImpl;
import com.telecome.service.impl.UserServiceImpl;
import com.telecome.utils.PageBean;

/**
 * Servlet implementation class WorkerUserServlet
 */
@WebServlet("/workerUserServlet")
public class WorkerUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUserService userService = new UserServiceImpl();
	private ITypeService typeService = new TypeServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkerUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if("add".equals(method)) {
			//去添加编辑详情页面
			add(request,response);
		}else if("addToReserve".equals(method)) {
			//添加完毕，去保存数据
			addToReserve(request,response);
		}else if("query".equals(method)) {
			//查询(包括模糊)
			query(request,response);
		}else if("delete".equals(method)) {
			//删除
			delete(request,response);
		}else if("updateOrDelete".equals(method)) {
			//去修改或删除选择页面(包括模糊查询)
			updateOrDelete(request,response);
		}else if("update".equals(method)) {
			//修改
			update(request,response);
		}else if("updateToReserve".equals(method)) {
			//修改完毕，去保存数据
			updateToReserve(request,response);
		}
		
		
	}
	
	/**
	 * 修改完毕，去保存数据
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void updateToReserve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String account = request.getParameter("account");
		String remain_money = request.getParameter("remain_money");
		String type = request.getParameter("type");
		String id = (String) request.getSession().getAttribute("id");
		int count = 0;
		
		try {
			count = userService.updateToReserve(phone,address,account,remain_money,type,id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(count > 0) {
			request.setAttribute("msg", "修改用户信息成功！");
			request.getRequestDispatcher("/workerUserServlet?method=query").forward(request, response);
		}else {
			System.out.println("修改用户信息出错！");
		}
		
		
			

	}

	
	/**
	 * 去修改页面（先获取数据再去修改页面）
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		request.getSession().setAttribute("id", id);
		int count = 0;
		User user = null;
		List type = new ArrayList();
		Worker worker = (Worker) request.getSession().getAttribute("worker");
//		if(worker != null) {}
			try {
				user = userService.queryById(id);
				type = typeService.getCustomerType();
				

			} catch (SQLException e) {
				e.printStackTrace();
			}
			
				//转发到修改的详情页面
				request.setAttribute("user", user);
				request.getSession().setAttribute("customerType",type);
				request.getRequestDispatcher("/WEB-INF/view/worker_user_update.jsp").forward(request, response);
		
//			else {
//			request.setAttribute("msg", "操作失败，请先登录！");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}

	}
	
	/**
	 * 	去修改或删除的选择页面(包括模糊查询)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void updateOrDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		Worker worker = (Worker) request.getSession().getAttribute("worker");
//		if(worker != null) {}
			try {
				//获取总条数并把总条数放入pageBean
				pageBean = userService.getTotalNotice(pageBean);
				//获取分页查询数据,并把获取到的数据集合放入pageBean
				pageBean = userService.query(pageBean);
				request.setAttribute("notices", pageBean);
			} catch (SQLException e) {
				e.printStackTrace();
			}
				request.setAttribute("user", pageBean.getT());
				request.getRequestDispatcher("/WEB-INF/view/worker_user_updateOrDelete.jsp").forward(request, response);
			
		
//		else {
//			request.setAttribute("msg", "操作失败，请先登录！");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}
		
	}
	
	/**
	 * 删除用户信息
	 * @param request
	 * @param response
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String id = request.getParameter("id");
		int count = 0;
		Worker worker = (Worker) request.getSession().getAttribute("worker");
//		if(worker != null) {}
		
		if(!"".equals("id") && null != id) {
			try {
				count = userService.delete(id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(count > 0) {
				request.setAttribute("msg", "删除客户信息成功！");
				request.getRequestDispatcher("workerUserServlet?method=updateOrDelete").forward(request, response);
			}else {
				request.setAttribute("msg", "删除出错，请检查！");
				request.getRequestDispatcher("workerUserServlet?method=updateOrDelete").forward(request, response);
				}
		}else{
			request.setAttribute("msg", "删除,获取id出错，请检查！");
			request.getRequestDispatcher("workerUserServlet?method=updateOrDelete").forward(request, response);
		}
			
			
		
//			else {
//			request.setAttribute("msg", "操作失败，请先登录！");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}
	}
	
	/**
	 * 	用户查询(包括模糊查询)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		Worker worker = (Worker) request.getSession().getAttribute("worker");
//		if(worker != null) {}
			try {
				//获取总条数并把总条数放入pageBean
				pageBean = userService.getTotalNotice(pageBean);
				//获取分页查询数据,并把获取到的数据集合放入pageBean
				pageBean = userService.query(pageBean);
				request.setAttribute("notices", pageBean);
			} catch (SQLException e) {
				e.printStackTrace();
			}
				request.setAttribute("user", pageBean.getT());
				request.getRequestDispatcher("/WEB-INF/view/worker_user_query.jsp").forward(request, response);
			
		
//		else {
//			request.setAttribute("msg", "操作失败，请先登录！");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}
		
	}
	
	/**
	 * 添加完毕，去保存数据
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addToReserve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String remain_money = request.getParameter("remain_money");
		String type = request.getParameter("type");
		int count = 0;
		
		try {
			count = userService.addToReserve(phone,address,account,password,remain_money,type);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(count > 0) {
			request.setAttribute("msg", "添加用户信息成功！");
			request.getRequestDispatcher("/workerUserServlet?method=query").forward(request, response);
		}else {
			System.out.println("添加用户信息出错！");
		}
		
		
			

	}


	/**
	 * 去添加编辑详情页面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List type = null;
		try {
			type = typeService.getCustomerType();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		if(type != null) {
			//获取4个用户类型
			request.getSession().setAttribute("customerType",type);
			//转发到添加的详情页面
			request.getRequestDispatcher("/WEB-INF/view/worker_user_add.jsp").forward(request, response);
		}
			

	}

}
