package com.telecome.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.telecome.po.Bill;
import com.telecome.po.User;
import com.telecome.service.IBillService;
import com.telecome.service.IUserService;
import com.telecome.service.impl.BillServiceImpl;
import com.telecome.service.impl.UserServiceImpl;

/**
 * Servlet implementation class GetBill
 */
@WebServlet("/getBillServlet")
public class GetBillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IBillService billService = new BillServiceImpl();
	private IUserService userService = new UserServiceImpl();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBillServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * ��绰��ϣ�����ͨ����¼�˵����洢
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Bill bill = new Bill();
		//ͨ��ʱ��
		String callTime = request.getParameter("callTime");
		//���к���
		String beCalledNumber = request.getParameter("beCalledNumber");
		//���к���
		String callNumber = request.getParameter("callNumber");
	
//		System.out.println(callTime);
//		System.out.println(beCalledNumber);
//		System.out.println(callNumber);
		//ͨ������ʱ��(���ݿ�Ĭ�ϵ�ǰʱ��,����Ҫ����)
		//String callDate = null;
		
		//�˵���(javaBean�Զ�����)
		String id = bill.getId();
		
		//����(ȥdao������)
		int cost = 0;
		
		bill.setTime(callTime);
		bill.setBeCalledNumber(beCalledNumber);
		bill.setCallNumber(callNumber);
		bill.setId(id);
		bill.setCost(cost);
		User user = (User) request.getSession().getAttribute("user");
		if(!"".equals(beCalledNumber) && null != beCalledNumber && !"".equals(callNumber) && null != callNumber) {
			int count = 0;
			try {
				count = billService.getBill(bill,user);
				//�ı���user��������ݣ�����һ��session
				user = userService.getUser(user.getUser_phone());
				request.getSession().setAttribute("user", user);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(count > 0) {
				request.getRequestDispatcher("/WEB-INF/view/user_call.jsp").forward(request, response);
			}else {
				System.out.println("getBillServlet����");
			}
		}
	}

}
