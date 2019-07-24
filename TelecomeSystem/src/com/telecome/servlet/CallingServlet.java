package com.telecome.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.telecome.po.User;

/**
 * Servlet implementation class CallingServlet
 */
@WebServlet("/callingServlet")
public class CallingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CallingServlet() {
        super();
       
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String beCalledNumber = request.getParameter("beCalledNumber");
		String callNumber = request.getParameter("callNumber");
//		System.out.println(beCalledNumber);
//		System.out.println(callNumber);
//		long startTime = new Date().getTime();//通话开始时间
//		request.setAttribute("startTime", startTime);
		request.setAttribute("beCalledNumber", beCalledNumber);
		request.setAttribute("callNumber", callNumber);
	
		if(!"".equals(callNumber) && null != callNumber && !"".equals(beCalledNumber) && null != beCalledNumber) {
			if(beCalledNumber.matches("1[0-9]{10}") && callNumber.matches("1[0-9]{10}")) {
				User user = (User) request.getSession().getAttribute("user");
				if(user.getUser_money() > 5) {//余额大于5才能打电话
					request.getRequestDispatcher("/WEB-INF/view/user_calling.jsp").forward(request, response);
				}else {
					request.setAttribute("msg", "余额不足，请先充值!");
					request.getRequestDispatcher("/callServlet").forward(request, response);
				}
				
			}else {
				request.setAttribute("msg", "非法号码！");
				request.getRequestDispatcher("/callServlet").forward(request, response);
			}
			
		}else {
			request.setAttribute("msg", "主叫号码和被叫号码不允许为空！");
			request.getRequestDispatcher("/callServlet").forward(request, response);;
		}
	}

}
