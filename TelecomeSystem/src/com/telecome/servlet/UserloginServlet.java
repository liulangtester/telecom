package com.telecome.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.telecome.po.User;
import com.telecome.service.IUserService;
import com.telecome.service.impl.UserServiceImpl;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/userLoginServlet")
public class UserloginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IUserService userService = new UserServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserloginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String validcode = request.getParameter("code");
		
		HttpSession session = request.getSession();
		String check_code = (String)session.getAttribute("check_code");//获取随机生成的验证码
		if(validcode.equals(check_code)) {
			if(!"".equals(username) && null != username && !"".equals(password) && null != password) {
				try {
					User user = userService.getUser(username,password);
					if(user != null) {
						request.getSession().setAttribute("user", user);
						request.getRequestDispatcher("/user_home.jsp").forward(request, response);
					}else {
						request.setAttribute("msg", "用户名或密码错误！");
						request.getRequestDispatcher("/userlogin.jsp").forward(request, response);
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}else {
				request.setAttribute("msg", "用户名或密码不允许为空！");
				request.getRequestDispatcher("/userlogin.jsp").forward(request, response);
			}
		}else {
			request.setAttribute("msg", "验证码错误！");
			request.getRequestDispatcher("/userlogin.jsp").forward(request, response);
		}
	
		
	}

}
