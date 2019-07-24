package com.telecome.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.telecome.po.Account;
import com.telecome.po.Worker;
import com.telecome.service.IAccountService;
import com.telecome.service.impl.AccountServiceImpl;
import com.telecome.utils.PageBean;

/**
 * Servlet implementation class WorkerAccountServlet
 */
@WebServlet("/workerAccountServlet")
public class WorkerAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IAccountService accountService = new AccountServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkerAccountServlet() {
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
		}else if("addOrUpdateToReserve".equals(method)) {
			//添加或修改完毕，保存数据到数据库
			addOrUpdateToReserve(request,response);
		}else if("updateOrDelete".equals(method)) {
			//去修改或删除选择页面(包括模糊查询)
			updateOrDelete(request,response);
		}else if("update".equals(method)) {
			//去修改页面（先获取数据再去修改页面）
			update(request,response);
		}else if("delete".equals(method)) {
			//去修改页面（先获取数据再去修改页面）
			delete(request,response);
		}else if("query".equals(method)) {
			//去查询页面
			query(request,response);
		}
		
	}
	
	/**
	 * 	账户查询(包括模糊查询)
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
				pageBean = accountService.getTotalNotice(pageBean);
				//获取分页查询数据,并把获取到的数据集合放入pageBean
				pageBean = accountService.query(pageBean);
				request.setAttribute("notices", pageBean);
			} catch (SQLException e) {
				e.printStackTrace();
			}
				request.setAttribute("account", pageBean.getT());
				request.getRequestDispatcher("/WEB-INF/view/worker_account_query.jsp").forward(request, response);
			
		
//		else {
//			request.setAttribute("msg", "操作失败，请先登录！");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}
		
	}
	
	/**
	 * 删除账户信息
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
				count = accountService.delete(id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(count > 0) {
				request.setAttribute("msg", "删除客户信息成功！");
				request.getRequestDispatcher("workerAccountServlet?method=updateOrDelete").forward(request, response);
			}else {
				request.setAttribute("msg", "删除出错，请检查！");
				request.getRequestDispatcher("workerAccountServlet?method=updateOrDelete").forward(request, response);
				}
		}else{
			request.setAttribute("msg", "删除,获取id出错，请检查！");
			request.getRequestDispatcher("workerAccountServlet?method=updateOrDelete").forward(request, response);
		}
			
			
		
//			else {
//			request.setAttribute("msg", "操作失败，请先登录！");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}
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
		Account account = null;
		Worker worker = (Worker) request.getSession().getAttribute("worker");
//		if(worker != null) {}
			try {
				account = accountService.queryById(id);

				String flag = "update";
				request.getSession().setAttribute("flag", flag);//修改标记

			} catch (SQLException e) {
				e.printStackTrace();
			}
			
				//转发到修改的详情页面（添加和修改同一个页面）
				request.setAttribute("account", account);
				request.getRequestDispatcher("/WEB-INF/view/worker_account_addOrUpdate.jsp").forward(request, response);
		
//			else {
//			request.setAttribute("msg", "操作失败，请先登录！");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}

	}
	
	/**
	 * 	去修改或删除选择页面(包括模糊查询)
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
				pageBean = accountService.getTotalNotice(pageBean);
				//获取分页查询数据,并把获取到的数据集合放入pageBean
				pageBean = accountService.query(pageBean);
				request.setAttribute("notices", pageBean);
			} catch (SQLException e) {
				e.printStackTrace();
			}
				request.setAttribute("account", pageBean.getT());
				request.getRequestDispatcher("/WEB-INF/view/worker_account_updateOrDelete.jsp").forward(request, response);
			
		
//		else {
//			request.setAttribute("msg", "操作失败，请先登录！");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}
		
	}

	/**
	 * 添加或修改完毕，保存数据到数据库
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addOrUpdateToReserve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String phone = request.getParameter("phone");
		String name = request.getParameter("name");
		String account = request.getParameter("account");
		String bank = request.getParameter("bank");
		String s_bank = request.getParameter("s_bank");
		String remain_money = request.getParameter("remain_money");
		String total_cost = request.getParameter("total_cost");
		String month_cost = request.getParameter("month_cost");
		String id = (String) request.getSession().getAttribute("id");
		
		String flag = (String) request.getSession().getAttribute("flag");//获取标记()
		int count = 0;
//		System.out.println(flag);
		if(flag != null) {
			//表明是修改，而不是添加,并且销毁标记，避免影响判断
			request.getSession().removeAttribute("flag");
			try {
				count = accountService.updateToReserve(phone,name,account,bank,s_bank,remain_money,total_cost,month_cost,id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(count > 0) {
				request.setAttribute("msg", "修改账户资料成功！");
				request.getRequestDispatcher("workerAccountServlet?method=updateOrDelete").forward(request, response);
			}else {
				request.setAttribute("msg", "修改账户资料出错，请检查！");
				request.getRequestDispatcher("workerAccountServlet?method=updateOrDelete").forward(request, response);
			}
		}else {
			//表明是添加
			try {
				count = accountService.addToReserve(phone,name,account,bank,s_bank,remain_money,total_cost,month_cost);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(count > 0) {
				request.setAttribute("msg", "添加账户资料成功！");
				request.getRequestDispatcher("workerAccountServlet?method=updateOrDelete").forward(request, response);
			}else {
				request.setAttribute("msg", "添加账户资料出错，请检查！");
				request.getRequestDispatcher("workerAccountServlet?method=updateOrDelete").forward(request, response);
			}
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

			//转发到添加的详情页面（添加和修改同一个页面）
			request.getRequestDispatcher("/WEB-INF/view/worker_account_addOrUpdate.jsp").forward(request, response);

	}
	
	
	
	
	
}
