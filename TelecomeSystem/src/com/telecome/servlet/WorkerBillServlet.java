package com.telecome.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.telecome.po.User;
import com.telecome.po.Worker;
import com.telecome.service.IBillService;
import com.telecome.service.IRechargeRecordService;
import com.telecome.service.impl.BillServiceImpl;
import com.telecome.service.impl.RechargeRecordServiceImpl;
import com.telecome.utils.PageBean;

/**
 * Servlet implementation class WorkerBillServlet
 */
@WebServlet("/workerBillServlet")
public class WorkerBillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IBillService billService = new BillServiceImpl();
	private IRechargeRecordService rechargeRecordService = new RechargeRecordServiceImpl();
		
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkerBillServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if("callRecord".equals(method)) {
			//��ѯͨ����¼
			callRecord(request,response);
		}else if("rechargeRecord".equals(method)) {
			rechargeRecord(request,response);
		}
	}
	
	/**
	 * ��ѯ��ֵ��¼
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void rechargeRecord(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
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
		
	
			try {
				//��ȡ��������������������pageBean
				pageBean = rechargeRecordService.getTotalNotice(pageBean);
				//��ȡ��ҳ��ѯ����,���ѻ�ȡ�������ݼ��Ϸ���pageBean
				pageBean = rechargeRecordService.query(pageBean);
				request.setAttribute("notices", pageBean);
				request.setAttribute("rechargeRecord", pageBean.getT());
				request.getRequestDispatcher("/WEB-INF/view/worker_bill_recharge_record.jsp").forward(request, response);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
						
			
		
	}
		
	
	
	/**
	 * ��ѯͨ����¼
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void callRecord(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
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
		
		User user = (User) request.getSession().getAttribute("user");
		
			try {
				//��ȡ��������������������pageBean
				pageBean = billService.getTotalNotice(pageBean);
				//��ȡ��ҳ��ѯ����,���ѻ�ȡ�������ݼ��Ϸ���pageBean
				pageBean = billService.query(pageBean);
				request.setAttribute("notices", pageBean);
				request.setAttribute("bill", pageBean.getT());
				request.getRequestDispatcher("/WEB-INF/view/worker_bill_call_record.jsp").forward(request, response);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	
		
	}
}
