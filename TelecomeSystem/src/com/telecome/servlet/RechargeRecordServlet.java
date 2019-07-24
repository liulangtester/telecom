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
	 * ��ѯ��ֵ��¼������ģ����ѯ��
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		if(user != null) {
			try {
				//��ȡ��������������������pageBean
				pageBean = rechargeRecordService.getTotalNotice(pageBean,user);
				//��ȡ��ҳ��ѯ����,���ѻ�ȡ�������ݼ��Ϸ���pageBean
				pageBean = rechargeRecordService.query(pageBean,user);
				request.setAttribute("notices", pageBean);
				request.setAttribute("rechargeRecord", pageBean.getT());
				request.getRequestDispatcher("/WEB-INF/view/user_recharge_record.jsp").forward(request, response);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
						
			
		}else {
			request.setAttribute("msg", "����ʧ�ܣ����ȵ�¼��");
			request.getRequestDispatcher("/userlogin.jsp").forward(request, response);
		}	
	}
	
	
}


