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
			//ȥ��ӱ༭����ҳ��
			add(request,response);
		}else if("addToReserve".equals(method)) {
			//�����ϣ�ȥ��������
			addToReserve(request,response);
		}else if("query".equals(method)) {
			//��ѯ(����ģ��)
			query(request,response);
		}else if("delete".equals(method)) {
			//ɾ��
			delete(request,response);
		}else if("updateOrDelete".equals(method)) {
			//ȥ�޸Ļ�ɾ��ѡ��ҳ��(����ģ����ѯ)
			updateOrDelete(request,response);
		}else if("update".equals(method)) {
			//�޸�
			update(request,response);
		}else if("updateToReserve".equals(method)) {
			//�޸���ϣ�ȥ��������
			updateToReserve(request,response);
		}
		
		
	}
	
	/**
	 * �޸���ϣ�ȥ��������
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
			request.setAttribute("msg", "�޸��û���Ϣ�ɹ���");
			request.getRequestDispatcher("/workerUserServlet?method=query").forward(request, response);
		}else {
			System.out.println("�޸��û���Ϣ����");
		}
		
		
			

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
			
				//ת�����޸ĵ�����ҳ��
				request.setAttribute("user", user);
				request.getSession().setAttribute("customerType",type);
				request.getRequestDispatcher("/WEB-INF/view/worker_user_update.jsp").forward(request, response);
		
//			else {
//			request.setAttribute("msg", "����ʧ�ܣ����ȵ�¼��");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}

	}
	
	/**
	 * 	ȥ�޸Ļ�ɾ����ѡ��ҳ��(����ģ����ѯ)
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
				pageBean = userService.getTotalNotice(pageBean);
				//��ȡ��ҳ��ѯ����,���ѻ�ȡ�������ݼ��Ϸ���pageBean
				pageBean = userService.query(pageBean);
				request.setAttribute("notices", pageBean);
			} catch (SQLException e) {
				e.printStackTrace();
			}
				request.setAttribute("user", pageBean.getT());
				request.getRequestDispatcher("/WEB-INF/view/worker_user_updateOrDelete.jsp").forward(request, response);
			
		
//		else {
//			request.setAttribute("msg", "����ʧ�ܣ����ȵ�¼��");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}
		
	}
	
	/**
	 * ɾ���û���Ϣ
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
				request.setAttribute("msg", "ɾ���ͻ���Ϣ�ɹ���");
				request.getRequestDispatcher("workerUserServlet?method=updateOrDelete").forward(request, response);
			}else {
				request.setAttribute("msg", "ɾ���������飡");
				request.getRequestDispatcher("workerUserServlet?method=updateOrDelete").forward(request, response);
				}
		}else{
			request.setAttribute("msg", "ɾ��,��ȡid�������飡");
			request.getRequestDispatcher("workerUserServlet?method=updateOrDelete").forward(request, response);
		}
			
			
		
//			else {
//			request.setAttribute("msg", "����ʧ�ܣ����ȵ�¼��");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}
	}
	
	/**
	 * 	�û���ѯ(����ģ����ѯ)
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
				pageBean = userService.getTotalNotice(pageBean);
				//��ȡ��ҳ��ѯ����,���ѻ�ȡ�������ݼ��Ϸ���pageBean
				pageBean = userService.query(pageBean);
				request.setAttribute("notices", pageBean);
			} catch (SQLException e) {
				e.printStackTrace();
			}
				request.setAttribute("user", pageBean.getT());
				request.getRequestDispatcher("/WEB-INF/view/worker_user_query.jsp").forward(request, response);
			
		
//		else {
//			request.setAttribute("msg", "����ʧ�ܣ����ȵ�¼��");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}
		
	}
	
	/**
	 * �����ϣ�ȥ��������
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
			request.setAttribute("msg", "����û���Ϣ�ɹ���");
			request.getRequestDispatcher("/workerUserServlet?method=query").forward(request, response);
		}else {
			System.out.println("����û���Ϣ����");
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
		List type = null;
		try {
			type = typeService.getCustomerType();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		if(type != null) {
			//��ȡ4���û�����
			request.getSession().setAttribute("customerType",type);
			//ת������ӵ�����ҳ��
			request.getRequestDispatcher("/WEB-INF/view/worker_user_add.jsp").forward(request, response);
		}
			

	}

}
