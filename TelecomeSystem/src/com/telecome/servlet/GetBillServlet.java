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
	 * 打电话完毕，生成通话记录账单并存储
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Bill bill = new Bill();
		//通话时长
		String callTime = request.getParameter("callTime");
		//被叫号码
		String beCalledNumber = request.getParameter("beCalledNumber");
		//主叫号码
		String callNumber = request.getParameter("callNumber");
	
//		System.out.println(callTime);
//		System.out.println(beCalledNumber);
//		System.out.println(callNumber);
		//通话结束时间(数据库默认当前时间,不需要设置)
		//String callDate = null;
		
		//账单号(javaBean自动生成)
		String id = bill.getId();
		
		//话费(去dao层生成)
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
				//改变了user里面的内容，更新一下session
				user = userService.getUser(user.getUser_phone());
				request.getSession().setAttribute("user", user);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(count > 0) {
				request.getRequestDispatcher("/WEB-INF/view/user_call.jsp").forward(request, response);
			}else {
				System.out.println("getBillServlet出错");
			}
		}
	}

}
