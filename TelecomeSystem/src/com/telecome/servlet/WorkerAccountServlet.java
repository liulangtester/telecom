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
			//ȥ��ӱ༭����ҳ��
			add(request,response);
		}else if("addOrUpdateToReserve".equals(method)) {
			//��ӻ��޸���ϣ��������ݵ����ݿ�
			addOrUpdateToReserve(request,response);
		}else if("updateOrDelete".equals(method)) {
			//ȥ�޸Ļ�ɾ��ѡ��ҳ��(����ģ����ѯ)
			updateOrDelete(request,response);
		}else if("update".equals(method)) {
			//ȥ�޸�ҳ�棨�Ȼ�ȡ������ȥ�޸�ҳ�棩
			update(request,response);
		}else if("delete".equals(method)) {
			//ȥ�޸�ҳ�棨�Ȼ�ȡ������ȥ�޸�ҳ�棩
			delete(request,response);
		}else if("query".equals(method)) {
			//ȥ��ѯҳ��
			query(request,response);
		}
		
	}
	
	/**
	 * 	�˻���ѯ(����ģ����ѯ)
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
			//�������thisPage��������������ת����int����(��ҳע��),���û����Ϊ��һ�����ҳ��
			pageNum = Integer.parseInt(thisPage);
		}
		PageBean pageBean = new PageBean();
		pageBean.setPageNum(pageNum);
		pageBean.setSearch(search);
		pageBean.setPageSize(5);//ÿҳ��ʾ��
		Worker worker = (Worker) request.getSession().getAttribute("worker");
//		if(worker != null) {}
			try {
				//��ȡ��������������������pageBean
				pageBean = accountService.getTotalNotice(pageBean);
				//��ȡ��ҳ��ѯ����,���ѻ�ȡ�������ݼ��Ϸ���pageBean
				pageBean = accountService.query(pageBean);
				request.setAttribute("notices", pageBean);
			} catch (SQLException e) {
				e.printStackTrace();
			}
				request.setAttribute("account", pageBean.getT());
				request.getRequestDispatcher("/WEB-INF/view/worker_account_query.jsp").forward(request, response);
			
		
//		else {
//			request.setAttribute("msg", "����ʧ�ܣ����ȵ�¼��");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}
		
	}
	
	/**
	 * ɾ���˻���Ϣ
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
				request.setAttribute("msg", "ɾ���ͻ���Ϣ�ɹ���");
				request.getRequestDispatcher("workerAccountServlet?method=updateOrDelete").forward(request, response);
			}else {
				request.setAttribute("msg", "ɾ���������飡");
				request.getRequestDispatcher("workerAccountServlet?method=updateOrDelete").forward(request, response);
				}
		}else{
			request.setAttribute("msg", "ɾ��,��ȡid�������飡");
			request.getRequestDispatcher("workerAccountServlet?method=updateOrDelete").forward(request, response);
		}
			
			
		
//			else {
//			request.setAttribute("msg", "����ʧ�ܣ����ȵ�¼��");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}
	}
	
	/**
	 * ȥ�޸�ҳ�棨�Ȼ�ȡ������ȥ�޸�ҳ�棩
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
				request.getSession().setAttribute("flag", flag);//�޸ı��

			} catch (SQLException e) {
				e.printStackTrace();
			}
			
				//ת�����޸ĵ�����ҳ�棨��Ӻ��޸�ͬһ��ҳ�棩
				request.setAttribute("account", account);
				request.getRequestDispatcher("/WEB-INF/view/worker_account_addOrUpdate.jsp").forward(request, response);
		
//			else {
//			request.setAttribute("msg", "����ʧ�ܣ����ȵ�¼��");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}

	}
	
	/**
	 * 	ȥ�޸Ļ�ɾ��ѡ��ҳ��(����ģ����ѯ)
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
			//�������thisPage��������������ת����int����(��ҳע��),���û����Ϊ��һ�����ҳ��
			pageNum = Integer.parseInt(thisPage);
		}
		PageBean pageBean = new PageBean();
		pageBean.setPageNum(pageNum);
		pageBean.setSearch(search);
		pageBean.setPageSize(5);//ÿҳ��ʾ��
		Worker worker = (Worker) request.getSession().getAttribute("worker");
//		if(worker != null) {}
			try {
				//��ȡ��������������������pageBean
				pageBean = accountService.getTotalNotice(pageBean);
				//��ȡ��ҳ��ѯ����,���ѻ�ȡ�������ݼ��Ϸ���pageBean
				pageBean = accountService.query(pageBean);
				request.setAttribute("notices", pageBean);
			} catch (SQLException e) {
				e.printStackTrace();
			}
				request.setAttribute("account", pageBean.getT());
				request.getRequestDispatcher("/WEB-INF/view/worker_account_updateOrDelete.jsp").forward(request, response);
			
		
//		else {
//			request.setAttribute("msg", "����ʧ�ܣ����ȵ�¼��");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}
		
	}

	/**
	 * ��ӻ��޸���ϣ��������ݵ����ݿ�
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
		
		String flag = (String) request.getSession().getAttribute("flag");//��ȡ���()
		int count = 0;
//		System.out.println(flag);
		if(flag != null) {
			//�������޸ģ����������,�������ٱ�ǣ�����Ӱ���ж�
			request.getSession().removeAttribute("flag");
			try {
				count = accountService.updateToReserve(phone,name,account,bank,s_bank,remain_money,total_cost,month_cost,id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(count > 0) {
				request.setAttribute("msg", "�޸��˻����ϳɹ���");
				request.getRequestDispatcher("workerAccountServlet?method=updateOrDelete").forward(request, response);
			}else {
				request.setAttribute("msg", "�޸��˻����ϳ������飡");
				request.getRequestDispatcher("workerAccountServlet?method=updateOrDelete").forward(request, response);
			}
		}else {
			//���������
			try {
				count = accountService.addToReserve(phone,name,account,bank,s_bank,remain_money,total_cost,month_cost);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(count > 0) {
				request.setAttribute("msg", "����˻����ϳɹ���");
				request.getRequestDispatcher("workerAccountServlet?method=updateOrDelete").forward(request, response);
			}else {
				request.setAttribute("msg", "����˻����ϳ������飡");
				request.getRequestDispatcher("workerAccountServlet?method=updateOrDelete").forward(request, response);
			}
		}
	
		
	}

	/**
	 * ȥ��ӱ༭����ҳ��
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			//ת������ӵ�����ҳ�棨��Ӻ��޸�ͬһ��ҳ�棩
			request.getRequestDispatcher("/WEB-INF/view/worker_account_addOrUpdate.jsp").forward(request, response);

	}
	
	
	
	
	
}
