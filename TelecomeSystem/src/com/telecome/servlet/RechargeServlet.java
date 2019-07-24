package com.telecome.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.telecome.po.User;
import com.telecome.service.IRechargeService;
import com.telecome.service.IUserService;
import com.telecome.service.impl.RechargeServiceImpl;
import com.telecome.service.impl.UserServiceImpl;

/**
 * Servlet implementation class RechargeServlet
 */
@WebServlet("/rechargeServlet")
public class RechargeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IRechargeService rechargeService = new RechargeServiceImpl();
    private IUserService userService = new UserServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RechargeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 充值
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String money = request.getParameter("money");
		String account = request.getParameter("account");
		String rechargeNumber = request.getParameter("rechargeNumber");
		User user = (User) request.getSession().getAttribute("user");
		int count = 0;
		if(null != user) {
			if(!"".equals(money) && null != money && !"".equals(account) && null != account && !"".equals(rechargeNumber) && null != rechargeNumber) {
				
				//如果有传参过来就是充值中（去数据库添加充值记录并且更新user表的余额），否则就是去充值
				
				try {
						count = rechargeService.addRecord(money, account, rechargeNumber,user);
						
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if(count > 0) {
					//添加充值记录成功
					request.setAttribute("msg", "充值成功！");
					try {
						//充值成功，则更新session里面的user
						user = userService.getUser(user.getUser_phone());
						request.getSession().setAttribute("user", user);
					} catch (SQLException e) {		
						e.printStackTrace();
					}
					request.getRequestDispatcher("/WEB-INF/view/user_recharge.jsp").forward(request, response);
				}else {
					request.setAttribute("msg", "充值失败！");
					request.getRequestDispatcher("/WEB-INF/view/user_recharge.jsp").forward(request, response);
				}
			}
			if(count == 0) {
				//第一次进充值页面
				String[] bank = user.getUser_bank().split(";");
				request.getSession().setAttribute("bank", bank);
				request.getRequestDispatcher("/WEB-INF/view/user_recharge.jsp").forward(request, response);
			}
		
		}else {
			request.setAttribute("msg", "操作失败，请先登录！");
			request.getRequestDispatcher("/userlogin.jsp").forward(request, response);
		}
	
	}

}
