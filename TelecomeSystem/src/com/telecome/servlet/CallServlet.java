package com.telecome.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.telecome.po.User;

/**
 * Servlet implementation class CallServlet
 */
@WebServlet("/callServlet")
public class CallServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CallServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if(user != null) {
			request.getRequestDispatcher("/WEB-INF/view/user_call.jsp").forward(request, response);
		}else {
			request.setAttribute("msg", "²Ù×÷Ê§°Ü£¬ÇëÏÈµÇÂ¼£¡");
			request.getRequestDispatcher("/userlogin.jsp").forward(request, response);
		}
		
	}

}
