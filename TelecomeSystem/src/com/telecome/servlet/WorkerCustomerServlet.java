package com.telecome.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.telecome.po.BigCustomer;
import com.telecome.po.BusinessCustomer;
import com.telecome.po.PublicCustomer;
import com.telecome.po.SpecialCustomer;
import com.telecome.po.Worker;
import com.telecome.service.IBigCustomerService;
import com.telecome.service.IBusinessCustomerService;
import com.telecome.service.IIndustryService;
import com.telecome.service.IPublicCustomerService;
import com.telecome.service.ISpecialCustomerService;
import com.telecome.service.ITypeService;
import com.telecome.service.impl.BigCustomerServiceImpl;
import com.telecome.service.impl.BusinessCustomerServiceImpl;
import com.telecome.service.impl.IndustryServiceImpl;
import com.telecome.service.impl.PublicCustomerServiceImpl;
import com.telecome.service.impl.SpecialCustomerServiceImpl;
import com.telecome.service.impl.TypeServiceImpl;
import com.telecome.utils.PageBean;

/**
 * Servlet implementation class WorkerCustomerServlet
 */
@WebServlet("/workerCustomerServlet")
public class WorkerCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ITypeService typeService = new TypeServiceImpl();
	private IIndustryService industryService = new IndustryServiceImpl();
    private IBusinessCustomerService businessCustomerService = new BusinessCustomerServiceImpl();
    private IPublicCustomerService publicCustomerService = new PublicCustomerServiceImpl();
    private IBigCustomerService bigCustomerService = new BigCustomerServiceImpl();
    private ISpecialCustomerService specialCustomerService = new SpecialCustomerServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkerCustomerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		/*******************************************商客******************************************/
		if("add".equals(method)) {
			//添加(去选择客户类型页面)
			add(request,response);
		}else if("addBusiness".equals(method)){
			//添加商客客户信息(上一步选择完客户类型后跳到填写详情页 [修改也跳到这个页面] )
			addBusiness(request,response);
		}else if("addBusinessToReserve".equals(method)) {
			//添加或修改商客信息完毕(添加或修改保存数据到数据库[修改也跳到这个页面])
			addOrUpdateBusinessToReserve(request,response);
		}else if("query".equals(method)) {
			//查询客户信息(去选择客户类型页面)
			query(request,response);
		}else if("queryBusiness".equals(method)) {
			//查询商客客户信息(去查询商客客户信息详情页面)(包括模糊查询)
			queryBusiness(request,response);
		}else if("updateOrDelete".equals(method)) {
			//修改或删除客户信息(去选择客户类型页面)
			updateOrDelete(request,response);
		}else if("updateOrDeleteBusiness".equals(method)) {
			//修改或删除商客客户信息(去修改和删除商客信息详情页面) //里面有查询和模糊查询
			updateOrDeleteBusiness(request,response); 
		}else if("deleteBusiness".equals(method)) {
			//删除商客客户信息(点击删除即删除)
			deleteBusiness(request,response);
		}else if("updateBusiness".equals(method)) {
			//修改商客客户信息(点击修改后，获取选中信息进入商客客户信息详情编辑页面)
			updateBusiness(request,response);
		}
		
		/*******************************************公客******************************************/
		else if("addPublic".equals(method)) {
			//添加公客客户信息(上一步选择完客户类型后跳到填写详情页 [修改也跳到这个页面] )
			addPublic(request,response);
		}else if("addPublicToReserve".equals(method)) {
			//添加或修改商客信息完毕(添加或修改保存数据到数据库[修改也跳到这个页面])
			addOrUpdatePublicToReserve(request,response);
		}else if("queryPublic".equals(method)) {
			//查询公客客户信息(去查询公客客户信息详情页面)(包括模糊查询)
			queryPublic(request,response);
		}else if("deletePublic".equals(method)) {
			//删除公客客户信息(点击删除即删除)
			deletePublic(request,response);
		}else if("updatePublic".equals(method)) {
			//修改公客客户信息(点击修改后，获取选中信息进入商客客户信息详情编辑页面)
			updatePublic(request,response);
		}else if("updateOrDeletePublic".equals(method)) {
			//修改或删除商客客户信息(去修改和删除商客信息详情页面) //里面有查询和模糊查询
			updateOrDeletePublic(request,response); 
		}
		
		/*******************************************大客******************************************/
		else if("addBig".equals(method)) {
			//添加大客客户信息(上一步选择完客户类型后跳到填写详情页 [修改也跳到这个页面] )
			addBig(request,response);
		}else if("addBigToReserve".equals(method)) {
			//添加或修改大客信息完毕(添加或修改保存数据到数据库[修改也跳到这个页面])
			addOrUpdateBigToReserve(request,response);
		}else if("queryBig".equals(method)) {
			//查询大客客户信息(去查询大客客户信息详情页面)(包括模糊查询)
			queryBig(request,response);
		}else if("deleteBig".equals(method)) {  
			//删除大客客户信息(点击删除即删除)
			deleteBig(request,response);
		}else if("updateBig".equals(method)) {
			//修改大客客户信息(点击修改后，获取选中信息进入大客客户信息详情编辑页面)
			updateBig(request,response);
		}else if("updateOrDeleteBig".equals(method)) {
			//修改或删除商客客户信息(去修改和删除商客信息详情页面) //里面有查询和模糊查询
			updateOrDeleteBig(request,response); 
		}
		
		/*******************************************专客******************************************/
		else if("addSpecial".equals(method)) {
			//添加专客客户信息(上一步选择完客户类型后跳到填写详情页 [修改也跳到这个页面] )
			addSpecial(request,response);
		}else if("addSpecialToReserve".equals(method)) {
			//添加或修改专客信息完毕(添加或修改保存数据到数据库[修改也跳到这个页面])
			addOrUpdateSpecialToReserve(request,response);
		}else if("querySpecial".equals(method)) {
			//查询专客客户信息(去查询专客客户信息详情页面)(包括模糊查询)
			querySpecial(request,response);
		}else if("deleteSpecial".equals(method)) {  
			//删除专客客户信息(点击删除即删除)
			deleteSpecial(request,response);
		}else if("updateSpecial".equals(method)) {
			//修改专客客户信息(点击修改后，获取选中信息进入专客客户信息详情编辑页面)
			updateSpecial(request,response);
		}else if("updateOrDeleteSpecial".equals(method)) {
			//修改或删除专客客户信息(去修改和删除专客信息详情页面) //里面有查询和模糊查询
			updateOrDeleteSpecial(request,response); 
		}

	}
	
	/**
	 *  修改或删除专客客户信息(去修改和删除页面)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateOrDeleteSpecial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String search = request.getParameter("search");
		//kind参数，用来辨别模糊查询是修改删除页面的还是查询页面的。
		String kind = request.getParameter("kind");//模糊查询和查询混合，不需要了
		String thisPage = request.getParameter("thisPage");
		int pageNum = 1;
		if(!"".equals(thisPage) && null != thisPage) {
			//如果传了thisPage参数过来，把它转换成int类型(分页注释),如果没有则为第一区间的页数
			pageNum = Integer.parseInt(thisPage);
		}
		PageBean pageBean = new PageBean();
		pageBean.setPageNum(pageNum);
		pageBean.setSearch(search);
		pageBean.setPageSize(5);//每页显示数
		Worker worker = (Worker) request.getSession().getAttribute("worker");
//		if(worker != null) {}
			try {
				//获取总条数并把总条数放入pageBean
				pageBean = specialCustomerService.getTotalNotice(pageBean);
				//获取分页查询数据,并把获取到的数据集合放入pageBean
				pageBean = specialCustomerService.query(pageBean);
				request.setAttribute("notices", pageBean);
			} catch (SQLException e) {
				e.printStackTrace();
			}
				request.setAttribute("specialCustomer", pageBean.getT());
				request.getRequestDispatcher("/WEB-INF/view/worker_customer_updateOrDeleteSpecial.jsp").forward(request, response);
			
		
//		else {
//			request.setAttribute("msg", "操作失败，请先登录！");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}
		
	
		
	}
	
	/**
	 * 修改专客客户信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateSpecial(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String id = request.getParameter("id");
		request.getSession().setAttribute("id", id);
		int count = 0;
		SpecialCustomer specialCustomer = null;
		Worker worker = (Worker) request.getSession().getAttribute("worker");
//		if(worker != null) {}
			try {
				specialCustomer = specialCustomerService.queryById(id);

				String flag = "update";
				request.getSession().setAttribute("flag", flag);//修改标记

			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(specialCustomer != null) {
				request.setAttribute("specialCustomer", specialCustomer);	
				//转发到添加的详情页面（添加和修改同一个页面）
				request.getRequestDispatcher("workerCustomerServlet?method=addSpecial").forward(request, response);
			}else {
				request.setAttribute("msg", "修改出错，请检查！");
				request.getRequestDispatcher("/worker_home.jsp").forward(request, response);
			
		}
//			else {
//			request.setAttribute("msg", "操作失败，请先登录！");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}
	}
	
	
	/**
	 * 删除专客客户信息
	 * @param request
	 * @param response
	 */
	private void deleteSpecial(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String id = request.getParameter("id");
		int count = 0;
		Worker worker = (Worker) request.getSession().getAttribute("worker");
//		if(worker != null) {}
		
		if(!"".equals("id") && null != id) {
			try {
				count = specialCustomerService.delete(id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(count > 0) {
				request.setAttribute("msg", "删除客户信息成功！");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeleteSpecial").forward(request, response);
			}else {
				request.setAttribute("msg", "删除出错，请检查！");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeleteSpecial").forward(request, response);
				}
		}else{
			request.setAttribute("msg", "删除,获取id出错，请检查！");
			request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeleteSpecial").forward(request, response);
		}
			
			
		
//			else {
//			request.setAttribute("msg", "操作失败，请先登录！");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}
	}
	
	/**
	 * 查询专客客户信息(包括模糊查询)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	private void querySpecial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String search = request.getParameter("search");
		//kind参数，用来辨别模糊查询是修改删除页面的还是查询页面的。
		String kind = request.getParameter("kind");
		String thisPage = request.getParameter("thisPage");
		int pageNum = 1;
		if(!"".equals(thisPage) && null != thisPage) {
			//如果传了thisPage参数过来，把它转换成int类型(分页注释),如果没有则为第一区间的页数
			pageNum = Integer.parseInt(thisPage);
		}
		PageBean pageBean = new PageBean();
		pageBean.setPageNum(pageNum);
		pageBean.setSearch(search);
		pageBean.setPageSize(5);//每页显示数
		Worker worker = (Worker) request.getSession().getAttribute("worker");
//		if(worker != null) {}
			try {
				//获取总条数并把总条数放入pageBean
				pageBean = specialCustomerService.getTotalNotice(pageBean);
				//获取分页查询数据,并把获取到的数据集合放入pageBean
				pageBean = specialCustomerService.query(pageBean);
				request.setAttribute("notices", pageBean);
				request.setAttribute("specialCustomer", pageBean.getT());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
				if("updateOrDelete".equals(kind)) {
					request.getRequestDispatcher("/WEB-INF/view/worker_customer_updateOrDeleteSpecial.jsp").forward(request, response);
				}else {
					request.getRequestDispatcher("/WEB-INF/view/worker_customer_querySpecial.jsp").forward(request, response);
				}
				
		
		
//		else {
//			request.setAttribute("msg", "操作失败，请先登录！");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}
		
		
		
	}
	
	/**
	 * 添加专客客户信息完毕，保存数据到数据库
	 * @param request
	 * @param response
	 */
	private void addOrUpdateSpecialToReserve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String phone = request.getParameter("phone");
		String name = request.getParameter("name");
		String customerType = request.getParameter("customerType");
		String address = request.getParameter("address");
		String manager = request.getParameter("manager");

		String id = (String) request.getSession().getAttribute("id");//修改选中的id
		String flag = (String) request.getSession().getAttribute("flag");//获取标记
		int count = 0;
//		System.out.println(flag);
		if(flag != null) {
			//表明是修改，而不是添加,并且销毁标记，避免影响判断
			request.getSession().removeAttribute("flag");
			try {
				count = specialCustomerService.updateToReserve(phone,name,customerType,address,id,manager);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(count > 0) {
				request.setAttribute("msg", "修改客户资料成功！");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeleteSpecial").forward(request, response);
			}else {
				request.setAttribute("msg", "修改客户资料出错，请检查！");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeleteSpecial").forward(request, response);
			}
		}else {
			//表明是添加
			try {
				count = specialCustomerService.addToReserve(phone,name,customerType,address,manager);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(count > 0) {
				request.setAttribute("msg", "添加客户资料成功！");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeleteSpecial").forward(request, response);
			}else {
				request.setAttribute("msg", "添加客户资料出错，请检查！");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeleteSpecial").forward(request, response);
			}
		}
	
	}
	
	/**
	 * 添加专客客户信息，跳往填写详情页
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void addSpecial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String customerType = request.getParameter("type");
		//把客户类型放在session里，后面jsp页面取
		request.getSession().setAttribute("customerType", customerType);
		//获取下拉菜单要显示的行业类型
		List type = null;
		try {
			type = industryService.getIndustryType();
		} catch (SQLException e) {	
			e.printStackTrace();
		}
		if(type != null) {
			//把行业类型存在session中
			request.getSession().setAttribute("industryType", type);
			request.getRequestDispatcher("/WEB-INF/view/worker_customer_addSpecial.jsp").forward(request, response);
		}
		
		
	}
	
	
	/**
	 *  修改或删除大客客户信息(去修改和删除页面)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateOrDeleteBig(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String search = request.getParameter("search");
		//kind参数，用来辨别模糊查询是修改删除页面的还是查询页面的。
		String kind = request.getParameter("kind");
		String thisPage = request.getParameter("thisPage");
		int pageNum = 1;
		if(!"".equals(thisPage) && null != thisPage) {
			//如果传了thisPage参数过来，把它转换成int类型(分页注释),如果没有则为第一区间的页数
			pageNum = Integer.parseInt(thisPage);
		}
		PageBean pageBean = new PageBean();
		pageBean.setPageNum(pageNum);
		pageBean.setSearch(search);
		pageBean.setPageSize(5);//每页显示数
		Worker worker = (Worker) request.getSession().getAttribute("worker");
//		if(worker != null) {}
			try {
				//获取总条数并把总条数放入pageBean
				pageBean = bigCustomerService.getTotalNotice(pageBean);
				//获取分页查询数据,并把获取到的数据集合放入pageBean
				pageBean = bigCustomerService.query(pageBean);
				request.setAttribute("notices", pageBean);
			} catch (SQLException e) {
				e.printStackTrace();
			}
				request.setAttribute("bigCustomer", pageBean.getT());
				request.getRequestDispatcher("/WEB-INF/view/worker_customer_updateOrDeleteBig.jsp").forward(request, response);
			
		
//		else {
//			request.setAttribute("msg", "操作失败，请先登录！");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}
		
	
		
	}
	
	/**
	 * 修改大客客户信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateBig(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String id = request.getParameter("id");
		request.getSession().setAttribute("id", id);
		int count = 0;
		BigCustomer bigCustomer = null;
		Worker worker = (Worker) request.getSession().getAttribute("worker");
//		if(worker != null) {}
			try {
				bigCustomer = bigCustomerService.queryById(id);

				String flag = "update";
				request.getSession().setAttribute("flag", flag);//修改标记

			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(bigCustomer != null) {
				request.setAttribute("bigCustomer", bigCustomer);	
				//转发到添加的详情页面（添加和修改同一个页面）
				request.getRequestDispatcher("workerCustomerServlet?method=addBig").forward(request, response);
			}else {
				request.setAttribute("msg", "修改出错，请检查！");
				request.getRequestDispatcher("/worker_home.jsp").forward(request, response);
			
		}
//			else {
//			request.setAttribute("msg", "操作失败，请先登录！");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}
	}
	
	/**
	 * 删除大客客户信息
	 * @param request
	 * @param response
	 */
	private void deleteBig(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String id = request.getParameter("id");
		int count = 0;
		Worker worker = (Worker) request.getSession().getAttribute("worker");
//		if(worker != null) {}
		
		if(!"".equals("id") && null != id) {
			try {
				count = bigCustomerService.delete(id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(count > 0) {
				request.setAttribute("msg", "删除客户信息成功！");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeleteBig").forward(request, response);
			}else {
				request.setAttribute("msg", "删除出错，请检查！");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeleteBig").forward(request, response);
				}
		}else{
			request.setAttribute("msg", "删除,获取id出错，请检查！");
			request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeleteBig").forward(request, response);
		}
			
			
		
//			else {
//			request.setAttribute("msg", "操作失败，请先登录！");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}
	}
	
	/**
	 * 查询大客客户信息(包括模糊查询)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	private void queryBig(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String search = request.getParameter("search");
		//kind参数，用来辨别模糊查询是修改删除页面的还是查询页面的。
		String kind = request.getParameter("kind");
		String thisPage = request.getParameter("thisPage");
		int pageNum = 1;
		if(!"".equals(thisPage) && null != thisPage) {
			//如果传了thisPage参数过来，把它转换成int类型(分页注释),如果没有则为第一区间的页数
			pageNum = Integer.parseInt(thisPage);
		}
		PageBean pageBean = new PageBean();
		pageBean.setPageNum(pageNum);
		pageBean.setSearch(search);
		pageBean.setPageSize(5);//每页显示数
		Worker worker = (Worker) request.getSession().getAttribute("worker");
//		if(worker != null) {}
			try {
				//获取总条数并把总条数放入pageBean
				pageBean = bigCustomerService.getTotalNotice(pageBean);
				//获取分页查询数据,并把获取到的数据集合放入pageBean
				pageBean = bigCustomerService.query(pageBean);
				request.setAttribute("notices", pageBean);
				request.setAttribute("bigCustomer", pageBean.getT());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
				if("updateOrDelete".equals(kind)) {
					request.getRequestDispatcher("/WEB-INF/view/worker_customer_updateOrDeleteBig.jsp").forward(request, response);
				}else {
					request.getRequestDispatcher("/WEB-INF/view/worker_customer_queryBig.jsp").forward(request, response);
				}
				
		
		
//		else {
//			request.setAttribute("msg", "操作失败，请先登录！");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}
		
		
		
	}
	
	/**
	 * 添加大客客户信息完毕，保存数据到数据库
	 * @param request
	 * @param response
	 */
	private void addOrUpdateBigToReserve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String phone = request.getParameter("phone");
		String name = request.getParameter("name");
		String customerType = request.getParameter("customerType");
		String address = request.getParameter("address");
		String manager = request.getParameter("manager");
		String industry = request.getParameter("industry");//这个参数没用到，数据库有默认值 
		String tc = request.getParameter("tc");
		String id = (String) request.getSession().getAttribute("id");//修改选中的id
		String flag = (String) request.getSession().getAttribute("flag");//获取标记
		int count = 0;
//		System.out.println(flag);
		if(flag != null) {
			//表明是修改，而不是添加,并且销毁标记，避免影响判断
			request.getSession().removeAttribute("flag");
			try {
				count = bigCustomerService.updateToReserve(phone,name,customerType,address,id,manager,industry,tc);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(count > 0) {
				request.setAttribute("msg", "修改客户资料成功！");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeleteBig").forward(request, response);
			}else {
				request.setAttribute("msg", "修改客户资料出错，请检查！");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeleteBig").forward(request, response);
			}
		}else {
			//表明是添加
			try {
				count = bigCustomerService.addToReserve(phone,name,customerType,address,manager,industry,tc);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(count > 0) {
				request.setAttribute("msg", "添加客户资料成功！");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeleteBig").forward(request, response);
			}else {
				request.setAttribute("msg", "添加客户资料出错，请检查！");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeleteBig").forward(request, response);
			}
		}
	
	}
	
	/**
	 * 添加大客客户信息，跳往填写详情页
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void addBig(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String customerType = request.getParameter("type");
		//把客户类型放在session里，后面jsp页面取
		request.getSession().setAttribute("customerType", customerType);
		//获取下拉菜单要显示的行业类型
		List type = null;
		try {
			type = industryService.getIndustryType();
		} catch (SQLException e) {	
			e.printStackTrace();
		}
		if(type != null) {
			//把行业类型存在session中
			request.getSession().setAttribute("industryType", type);
			request.getRequestDispatcher("/WEB-INF/view/worker_customer_addBig.jsp").forward(request, response);
		}
		
		
	}
	
	/**
	 *  修改或删除商客客户信息(去修改和删除页面)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateOrDeletePublic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String search = request.getParameter("search");
		//kind参数，用来辨别模糊查询是修改删除页面的还是查询页面的。
		String kind = request.getParameter("kind");
		String thisPage = request.getParameter("thisPage");
		int pageNum = 1;
		if(!"".equals(thisPage) && null != thisPage) {
			//如果传了thisPage参数过来，把它转换成int类型(分页注释),如果没有则为第一区间的页数
			pageNum = Integer.parseInt(thisPage);
		}
		PageBean pageBean = new PageBean();
		pageBean.setPageNum(pageNum);
		pageBean.setSearch(search);
		pageBean.setPageSize(5);//每页显示数
		Worker worker = (Worker) request.getSession().getAttribute("worker");
//		if(worker != null) {}
			try {
				//获取总条数并把总条数放入pageBean
				pageBean = publicCustomerService.getTotalNotice(pageBean);
				//获取分页查询数据,并把获取到的数据集合放入pageBean
				pageBean = publicCustomerService.query(pageBean);
				request.setAttribute("notices", pageBean);
			} catch (SQLException e) {
				e.printStackTrace();
			}
				request.setAttribute("publicCustomer", pageBean.getT());
				request.getRequestDispatcher("/WEB-INF/view/worker_customer_updateOrDeletePublic.jsp").forward(request, response);
			
		
//		else {
//			request.setAttribute("msg", "操作失败，请先登录！");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}
		
	
		
	}
	
	/**
	 * 修改公客客户信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updatePublic(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String id = request.getParameter("id");
		request.getSession().setAttribute("id", id);
		int count = 0;
		PublicCustomer publicCustomer = null;
		Worker worker = (Worker) request.getSession().getAttribute("worker");
//		if(worker != null) {}
			try {
				publicCustomer = publicCustomerService.queryById(id);

				String flag = "update";
				request.getSession().setAttribute("flag", flag);//修改标记

			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(publicCustomer != null) {
				request.setAttribute("publicCustomer", publicCustomer);	
				//转发到添加的详情页面（添加和修改同一个页面）
				request.getRequestDispatcher("workerCustomerServlet?method=addPublic").forward(request, response);
			}else {
				request.setAttribute("msg", "修改出错，请检查！");
				request.getRequestDispatcher("/worker_home.jsp").forward(request, response);
			
		}
//			else {
//			request.setAttribute("msg", "操作失败，请先登录！");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}
	}

	
	/**
	 * 删除公客客户信息
	 * @param request
	 * @param response
	 */
	private void deletePublic(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String id = request.getParameter("id");
		int count = 0;
		Worker worker = (Worker) request.getSession().getAttribute("worker");
//		if(worker != null) {}
		
		if(!"".equals("id") && null != id) {
			try {
				count = publicCustomerService.delete(id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(count > 0) {
				request.setAttribute("msg", "删除客户信息成功！");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeletePublic").forward(request, response);
			}else {
				request.setAttribute("msg", "删除出错，请检查！");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeletePublic").forward(request, response);
				}
		}else{
			request.setAttribute("msg", "删除,获取id出错，请检查！");
			request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeletePublic").forward(request, response);
		}
			
			
		
//			else {
//			request.setAttribute("msg", "操作失败，请先登录！");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}
	}
	
	/**
	 * 查询公客客户信息(包括模糊查询)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	private void queryPublic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String search = request.getParameter("search");
		//kind参数，用来辨别模糊查询是修改删除页面的还是查询页面的。
		String kind = request.getParameter("kind");
		String thisPage = request.getParameter("thisPage");
		int pageNum = 1;
		if(!"".equals(thisPage) && null != thisPage) {
			//如果传了thisPage参数过来，把它转换成int类型(分页注释),如果没有则为第一区间的页数
			pageNum = Integer.parseInt(thisPage);
		}
		PageBean pageBean = new PageBean();
		pageBean.setPageNum(pageNum);
		pageBean.setSearch(search);
		pageBean.setPageSize(5);//每页显示数
		Worker worker = (Worker) request.getSession().getAttribute("worker");
//		if(worker != null) {}
			try {
				//获取总条数并把总条数放入pageBean
				pageBean = publicCustomerService.getTotalNotice(pageBean);
				//获取分页查询数据,并把获取到的数据集合放入pageBean
				pageBean = publicCustomerService.query(pageBean);
				request.setAttribute("notices", pageBean);
				request.setAttribute("publicCustomer", pageBean.getT());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
				if("updateOrDelete".equals(kind)) {
					request.getRequestDispatcher("/WEB-INF/view/worker_customer_updateOrDeletePublic.jsp").forward(request, response);
				}else {
					request.getRequestDispatcher("/WEB-INF/view/worker_customer_queryPublic.jsp").forward(request, response);
				}
				
		
		
//		else {
//			request.setAttribute("msg", "操作失败，请先登录！");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}
		
		
		
	}
	
	/**
	 * 添加公客客户信息完毕，保存数据到数据库
	 * @param request
	 * @param response
	 */
	private void addOrUpdatePublicToReserve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String phone = request.getParameter("phone");
		String name = request.getParameter("name");
		String customerType = request.getParameter("customerType");
		String address = request.getParameter("address");

		String id = (String) request.getSession().getAttribute("id");//修改选中的id
		String flag = (String) request.getSession().getAttribute("flag");//获取标记
		int count = 0;
//		System.out.println(flag);
		if(flag != null) {
			//表明是修改，而不是添加,并且销毁标记，避免影响判断
			request.getSession().removeAttribute("flag");
			try {
				count = publicCustomerService.updateToReserve(phone,name,customerType,address,id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(count > 0) {
				request.setAttribute("msg", "修改客户资料成功！");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeletePublic").forward(request, response);
			}else {
				request.setAttribute("msg", "修改客户资料出错，请检查！");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeletePublic").forward(request, response);
			}
		}else {
			//表明是添加
			try {
				count = publicCustomerService.addToReserve(phone,name,customerType,address);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(count > 0) {
				request.setAttribute("msg", "添加客户资料成功！");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeletePublic").forward(request, response);
			}else {
				request.setAttribute("msg", "添加客户资料出错，请检查！");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeletePublic").forward(request, response);
			}
		}
	
	}
	
	/**
	 * 添加公客客户信息，跳往填写详情页
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void addPublic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String customerType = request.getParameter("type");
		//把客户类型放在session里，后面jsp页面取
		request.getSession().setAttribute("customerType", customerType);
//		//获取下拉菜单要显示的行业类型
//		List type = null;
//		try {
//			type = industryService.getIndustryType();
//		} catch (SQLException e) {	
//			e.printStackTrace();
//		}
//		if(type != null) {
//			//把行业类型存在session中
//			request.getSession().setAttribute("industryType", type);
			request.getRequestDispatcher("/WEB-INF/view/worker_customer_addPublic.jsp").forward(request, response);
//		}
		
		
	}
	
	/**
	 * 查询客户信息(去选择客户类型页面)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void query(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		List type = null;
		try {
			type = typeService.getCustomerType();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		if(type != null) {
			request.getSession().setAttribute("customerType",type);
			request.getRequestDispatcher("/WEB-INF/view/worker_customer_query.jsp").forward(request, response);
		}
		
	}

	/**
	 * 修改商客客户信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateBusiness(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String id = request.getParameter("id");
		request.getSession().setAttribute("id", id);
		int count = 0;
		BusinessCustomer businessCustomer = null;
		Worker worker = (Worker) request.getSession().getAttribute("worker");
//		if(worker != null) {}
			try {
				businessCustomer = businessCustomerService.queryById(id);
				if(businessCustomer != null) {
					//修改完毕，获取行业类型，放在session中，并且设置一个修改标记，来分辩是修改还是添加
					List type = industryService.getIndustryType();
					String flag = "update";
					request.getSession().setAttribute("flag", flag);//修改标记
					request.getSession().setAttribute("industryType", type);
				}
		
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(businessCustomer != null) {
				request.setAttribute("businessCustomer", businessCustomer);	
				//转发到添加的详情页面（添加和修改同一个页面）
				request.getRequestDispatcher("workerCustomerServlet?method=addBusiness").forward(request, response);
			}else {
				request.setAttribute("msg", "修改出错，请检查！");
				request.getRequestDispatcher("/worker_home.jsp").forward(request, response);
			
		}
//			else {
//			request.setAttribute("msg", "操作失败，请先登录！");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}
	}

	/**
	 * 删除商客客户信息
	 * @param request
	 * @param response
	 */
	private void deleteBusiness(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String id = request.getParameter("id");
		int count = 0;
		Worker worker = (Worker) request.getSession().getAttribute("worker");
//		if(worker != null) {}
		
		if(!"".equals("id") && null != id) {
			try {
				count = businessCustomerService.delete(id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(count > 0) {
				request.setAttribute("msg", "删除客户信息成功！");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeleteBusiness").forward(request, response);
			}else {
				request.setAttribute("msg", "删除出错，请检查！");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeleteBusiness").forward(request, response);
				}
		}else{
			request.setAttribute("msg", "删除,获取id出错，请检查！");
			request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeleteBusiness").forward(request, response);
		}
			
			
		
//			else {
//			request.setAttribute("msg", "操作失败，请先登录！");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}
	}
		/**
		 * 修改和删除(去选择客户类型页面)
		 */
		private void updateOrDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			List type = null;
			try {
				type = typeService.getCustomerType();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			if(type != null) {
				request.getSession().setAttribute("customerType",type);
				request.getRequestDispatcher("/WEB-INF/view/worker_customer_updateOrDelete.jsp").forward(request, response);
			}
			
		}
		

	/**
	 *  修改或删除商客客户信息(去修改和删除页面)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateOrDeleteBusiness(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String search = request.getParameter("search");
		//kind参数，用来辨别模糊查询是修改删除页面的还是查询页面的。
		String kind = request.getParameter("kind");
		String thisPage = request.getParameter("thisPage");
		int pageNum = 1;
		if(!"".equals(thisPage) && null != thisPage) {
			//如果传了thisPage参数过来，把它转换成int类型(分页注释),如果没有则为第一区间的页数
			pageNum = Integer.parseInt(thisPage);
		}
		PageBean pageBean = new PageBean();
		pageBean.setPageNum(pageNum);
		pageBean.setSearch(search);
		pageBean.setPageSize(5);//每页显示数
		Worker worker = (Worker) request.getSession().getAttribute("worker");
//		if(worker != null) {}
			try {
				//获取总条数并把总条数放入pageBean
				pageBean = businessCustomerService.getTotalNotice(pageBean);
				//获取分页查询数据,并把获取到的数据集合放入pageBean
				pageBean = businessCustomerService.query(pageBean);
				request.setAttribute("notices", pageBean);
			} catch (SQLException e) {
				e.printStackTrace();
			}
				request.setAttribute("businessCustomer", pageBean.getT());
				request.getRequestDispatcher("/WEB-INF/view/worker_customer_updateOrDeleteBusiness.jsp").forward(request, response);
			
		
//		else {
//			request.setAttribute("msg", "操作失败，请先登录！");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}
		
	
		
	}

//	/**
//	 *  查询客户信息(模糊查询) 这里合并到了查询里面，这个方法作废
//	 * @param request
//	 * @param response
//	 * @throws ServletException
//	 * @throws IOException
//	 */
//	private void queryBusinessByPart(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
//		String content = request.getParameter("content");
//		//kind参数，用来辨别模糊查询是修改删除页面的还是查询页面的。
//		String kind = request.getParameter("kind");
//		List list = null;
//		Worker worker = (Worker) request.getSession().getAttribute("worker");
////		if(worker != null) {}
//			try {
//				list = businessCustomerService.queryByPart(content);
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			
//			if(list != null) {
//				request.setAttribute("businessCustomer", list);
//				if("updateOrDelete".equals(kind)) {
//					request.getRequestDispatcher("/WEB-INF/view/worker_customer_updateOrDeleteBusiness.jsp").forward(request, response);
//				}else {
//					request.getRequestDispatcher("/WEB-INF/view/worker_customer_queryBusiness.jsp").forward(request, response);
//				}
//				
//			}else {
//				request.setAttribute("msg", "查询出错，请检查！");
//				request.getRequestDispatcher("/WEB-INF/view/worker_customer_query.jsp").forward(request, response);
//				}
////			else {
////			request.setAttribute("msg", "操作失败，请先登录！");
////			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
////		}
//	}

	/**
	 * 查询商客客户信息(包括模糊查询)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	private void queryBusiness(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String search = request.getParameter("search");
		//kind参数，用来辨别模糊查询是修改删除页面的还是查询页面的。
		String kind = request.getParameter("kind");
		String thisPage = request.getParameter("thisPage");
		int pageNum = 1;
		if(!"".equals(thisPage) && null != thisPage) {
			//如果传了thisPage参数过来，把它转换成int类型(分页注释),如果没有则为第一区间的页数
			pageNum = Integer.parseInt(thisPage);
		}
		PageBean pageBean = new PageBean();
		pageBean.setPageNum(pageNum);
		pageBean.setSearch(search);
		pageBean.setPageSize(5);//每页显示数
		Worker worker = (Worker) request.getSession().getAttribute("worker");
//		if(worker != null) {}
			try {
				//获取总条数并把总条数放入pageBean
				pageBean = businessCustomerService.getTotalNotice(pageBean);
				//获取分页查询数据,并把获取到的数据集合放入pageBean
				pageBean = businessCustomerService.query(pageBean);
				request.setAttribute("notices", pageBean);
				request.setAttribute("businessCustomer", pageBean.getT());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
				if("updateOrDelete".equals(kind)) {
					request.getRequestDispatcher("/WEB-INF/view/worker_customer_updateOrDeleteBusiness.jsp").forward(request, response);
				}else {
					request.getRequestDispatcher("/WEB-INF/view/worker_customer_queryBusiness.jsp").forward(request, response);
				}
				
		
		
//		else {
//			request.setAttribute("msg", "操作失败，请先登录！");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}
		
		
		
	}

	/**
	 * 添加商客客户信息完毕，保存数据到数据库
	 * @param request
	 * @param response
	 */
	private void addOrUpdateBusinessToReserve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String phone = request.getParameter("phone");
		String name = request.getParameter("name");
		String customerType = request.getParameter("customerType");
		String address = request.getParameter("address");
		String manager = request.getParameter("manager");
		String industry = request.getParameter("industry");//这个参数没用到，数据库有默认值 
		String id = (String) request.getSession().getAttribute("id");
		String flag = (String) request.getSession().getAttribute("flag");//获取标记
		int count = 0;
//		System.out.println(flag);
		if(flag != null) {
			//表明是修改，而不是添加,并且销毁标记，避免影响判断
			request.getSession().removeAttribute("flag");
			try {
				count = businessCustomerService.updateToReserve(phone,name,customerType,address,manager,industry,id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(count > 0) {
				request.setAttribute("msg", "修改客户资料成功！");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeleteBusiness").forward(request, response);
			}else {
				request.setAttribute("msg", "修改客户资料出错，请检查！");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeleteBusiness").forward(request, response);
			}
		}else {
			//表明是添加
			try {
				count = businessCustomerService.addToReserve(phone,name,customerType,address,manager,industry);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(count > 0) {
				request.setAttribute("msg", "添加客户资料成功！");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeleteBusiness").forward(request, response);
			}else {
				request.setAttribute("msg", "添加客户资料出错，请检查！");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeleteBusiness").forward(request, response);
			}
		}
	
	}

	/**
	 * 添加商客客户信息，跳往填写详情页
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void addBusiness(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String customerType = request.getParameter("type");
		//把客户类型放在session里，后面jsp页面取
		request.getSession().setAttribute("customerType", customerType);
		//获取下拉菜单要显示的行业类型
		List type = null;
		try {
			type = industryService.getIndustryType();
		} catch (SQLException e) {	
			e.printStackTrace();
		}
		if(type != null) {
			//把行业类型存在session中
			request.getSession().setAttribute("industryType", type);
			request.getRequestDispatcher("/WEB-INF/view/worker_customer_addBusiness.jsp").forward(request, response);
		}
		
		
	}

	/**
	 * 获取客户类型,跳转到添加页面的第一页
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
			request.getSession().setAttribute("customerType",type);
			request.getRequestDispatcher("/WEB-INF/view/worker_customer_add.jsp").forward(request, response);
		}
		
	}

}
