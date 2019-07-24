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
		/*******************************************�̿�******************************************/
		if("add".equals(method)) {
			//���(ȥѡ��ͻ�����ҳ��)
			add(request,response);
		}else if("addBusiness".equals(method)){
			//����̿Ϳͻ���Ϣ(��һ��ѡ����ͻ����ͺ�������д����ҳ [�޸�Ҳ�������ҳ��] )
			addBusiness(request,response);
		}else if("addBusinessToReserve".equals(method)) {
			//��ӻ��޸��̿���Ϣ���(��ӻ��޸ı������ݵ����ݿ�[�޸�Ҳ�������ҳ��])
			addOrUpdateBusinessToReserve(request,response);
		}else if("query".equals(method)) {
			//��ѯ�ͻ���Ϣ(ȥѡ��ͻ�����ҳ��)
			query(request,response);
		}else if("queryBusiness".equals(method)) {
			//��ѯ�̿Ϳͻ���Ϣ(ȥ��ѯ�̿Ϳͻ���Ϣ����ҳ��)(����ģ����ѯ)
			queryBusiness(request,response);
		}else if("updateOrDelete".equals(method)) {
			//�޸Ļ�ɾ���ͻ���Ϣ(ȥѡ��ͻ�����ҳ��)
			updateOrDelete(request,response);
		}else if("updateOrDeleteBusiness".equals(method)) {
			//�޸Ļ�ɾ���̿Ϳͻ���Ϣ(ȥ�޸ĺ�ɾ���̿���Ϣ����ҳ��) //�����в�ѯ��ģ����ѯ
			updateOrDeleteBusiness(request,response); 
		}else if("deleteBusiness".equals(method)) {
			//ɾ���̿Ϳͻ���Ϣ(���ɾ����ɾ��)
			deleteBusiness(request,response);
		}else if("updateBusiness".equals(method)) {
			//�޸��̿Ϳͻ���Ϣ(����޸ĺ󣬻�ȡѡ����Ϣ�����̿Ϳͻ���Ϣ����༭ҳ��)
			updateBusiness(request,response);
		}
		
		/*******************************************����******************************************/
		else if("addPublic".equals(method)) {
			//��ӹ��Ϳͻ���Ϣ(��һ��ѡ����ͻ����ͺ�������д����ҳ [�޸�Ҳ�������ҳ��] )
			addPublic(request,response);
		}else if("addPublicToReserve".equals(method)) {
			//��ӻ��޸��̿���Ϣ���(��ӻ��޸ı������ݵ����ݿ�[�޸�Ҳ�������ҳ��])
			addOrUpdatePublicToReserve(request,response);
		}else if("queryPublic".equals(method)) {
			//��ѯ���Ϳͻ���Ϣ(ȥ��ѯ���Ϳͻ���Ϣ����ҳ��)(����ģ����ѯ)
			queryPublic(request,response);
		}else if("deletePublic".equals(method)) {
			//ɾ�����Ϳͻ���Ϣ(���ɾ����ɾ��)
			deletePublic(request,response);
		}else if("updatePublic".equals(method)) {
			//�޸Ĺ��Ϳͻ���Ϣ(����޸ĺ󣬻�ȡѡ����Ϣ�����̿Ϳͻ���Ϣ����༭ҳ��)
			updatePublic(request,response);
		}else if("updateOrDeletePublic".equals(method)) {
			//�޸Ļ�ɾ���̿Ϳͻ���Ϣ(ȥ�޸ĺ�ɾ���̿���Ϣ����ҳ��) //�����в�ѯ��ģ����ѯ
			updateOrDeletePublic(request,response); 
		}
		
		/*******************************************���******************************************/
		else if("addBig".equals(method)) {
			//��Ӵ�Ϳͻ���Ϣ(��һ��ѡ����ͻ����ͺ�������д����ҳ [�޸�Ҳ�������ҳ��] )
			addBig(request,response);
		}else if("addBigToReserve".equals(method)) {
			//��ӻ��޸Ĵ����Ϣ���(��ӻ��޸ı������ݵ����ݿ�[�޸�Ҳ�������ҳ��])
			addOrUpdateBigToReserve(request,response);
		}else if("queryBig".equals(method)) {
			//��ѯ��Ϳͻ���Ϣ(ȥ��ѯ��Ϳͻ���Ϣ����ҳ��)(����ģ����ѯ)
			queryBig(request,response);
		}else if("deleteBig".equals(method)) {  
			//ɾ����Ϳͻ���Ϣ(���ɾ����ɾ��)
			deleteBig(request,response);
		}else if("updateBig".equals(method)) {
			//�޸Ĵ�Ϳͻ���Ϣ(����޸ĺ󣬻�ȡѡ����Ϣ�����Ϳͻ���Ϣ����༭ҳ��)
			updateBig(request,response);
		}else if("updateOrDeleteBig".equals(method)) {
			//�޸Ļ�ɾ���̿Ϳͻ���Ϣ(ȥ�޸ĺ�ɾ���̿���Ϣ����ҳ��) //�����в�ѯ��ģ����ѯ
			updateOrDeleteBig(request,response); 
		}
		
		/*******************************************ר��******************************************/
		else if("addSpecial".equals(method)) {
			//���ר�Ϳͻ���Ϣ(��һ��ѡ����ͻ����ͺ�������д����ҳ [�޸�Ҳ�������ҳ��] )
			addSpecial(request,response);
		}else if("addSpecialToReserve".equals(method)) {
			//��ӻ��޸�ר����Ϣ���(��ӻ��޸ı������ݵ����ݿ�[�޸�Ҳ�������ҳ��])
			addOrUpdateSpecialToReserve(request,response);
		}else if("querySpecial".equals(method)) {
			//��ѯר�Ϳͻ���Ϣ(ȥ��ѯר�Ϳͻ���Ϣ����ҳ��)(����ģ����ѯ)
			querySpecial(request,response);
		}else if("deleteSpecial".equals(method)) {  
			//ɾ��ר�Ϳͻ���Ϣ(���ɾ����ɾ��)
			deleteSpecial(request,response);
		}else if("updateSpecial".equals(method)) {
			//�޸�ר�Ϳͻ���Ϣ(����޸ĺ󣬻�ȡѡ����Ϣ����ר�Ϳͻ���Ϣ����༭ҳ��)
			updateSpecial(request,response);
		}else if("updateOrDeleteSpecial".equals(method)) {
			//�޸Ļ�ɾ��ר�Ϳͻ���Ϣ(ȥ�޸ĺ�ɾ��ר����Ϣ����ҳ��) //�����в�ѯ��ģ����ѯ
			updateOrDeleteSpecial(request,response); 
		}

	}
	
	/**
	 *  �޸Ļ�ɾ��ר�Ϳͻ���Ϣ(ȥ�޸ĺ�ɾ��ҳ��)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateOrDeleteSpecial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String search = request.getParameter("search");
		//kind�������������ģ����ѯ���޸�ɾ��ҳ��Ļ��ǲ�ѯҳ��ġ�
		String kind = request.getParameter("kind");//ģ����ѯ�Ͳ�ѯ��ϣ�����Ҫ��
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
				pageBean = specialCustomerService.getTotalNotice(pageBean);
				//��ȡ��ҳ��ѯ����,���ѻ�ȡ�������ݼ��Ϸ���pageBean
				pageBean = specialCustomerService.query(pageBean);
				request.setAttribute("notices", pageBean);
			} catch (SQLException e) {
				e.printStackTrace();
			}
				request.setAttribute("specialCustomer", pageBean.getT());
				request.getRequestDispatcher("/WEB-INF/view/worker_customer_updateOrDeleteSpecial.jsp").forward(request, response);
			
		
//		else {
//			request.setAttribute("msg", "����ʧ�ܣ����ȵ�¼��");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}
		
	
		
	}
	
	/**
	 * �޸�ר�Ϳͻ���Ϣ
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
				request.getSession().setAttribute("flag", flag);//�޸ı��

			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(specialCustomer != null) {
				request.setAttribute("specialCustomer", specialCustomer);	
				//ת������ӵ�����ҳ�棨��Ӻ��޸�ͬһ��ҳ�棩
				request.getRequestDispatcher("workerCustomerServlet?method=addSpecial").forward(request, response);
			}else {
				request.setAttribute("msg", "�޸ĳ������飡");
				request.getRequestDispatcher("/worker_home.jsp").forward(request, response);
			
		}
//			else {
//			request.setAttribute("msg", "����ʧ�ܣ����ȵ�¼��");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}
	}
	
	
	/**
	 * ɾ��ר�Ϳͻ���Ϣ
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
				request.setAttribute("msg", "ɾ���ͻ���Ϣ�ɹ���");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeleteSpecial").forward(request, response);
			}else {
				request.setAttribute("msg", "ɾ���������飡");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeleteSpecial").forward(request, response);
				}
		}else{
			request.setAttribute("msg", "ɾ��,��ȡid�������飡");
			request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeleteSpecial").forward(request, response);
		}
			
			
		
//			else {
//			request.setAttribute("msg", "����ʧ�ܣ����ȵ�¼��");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}
	}
	
	/**
	 * ��ѯר�Ϳͻ���Ϣ(����ģ����ѯ)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	private void querySpecial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String search = request.getParameter("search");
		//kind�������������ģ����ѯ���޸�ɾ��ҳ��Ļ��ǲ�ѯҳ��ġ�
		String kind = request.getParameter("kind");
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
				pageBean = specialCustomerService.getTotalNotice(pageBean);
				//��ȡ��ҳ��ѯ����,���ѻ�ȡ�������ݼ��Ϸ���pageBean
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
//			request.setAttribute("msg", "����ʧ�ܣ����ȵ�¼��");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}
		
		
		
	}
	
	/**
	 * ���ר�Ϳͻ���Ϣ��ϣ��������ݵ����ݿ�
	 * @param request
	 * @param response
	 */
	private void addOrUpdateSpecialToReserve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String phone = request.getParameter("phone");
		String name = request.getParameter("name");
		String customerType = request.getParameter("customerType");
		String address = request.getParameter("address");
		String manager = request.getParameter("manager");

		String id = (String) request.getSession().getAttribute("id");//�޸�ѡ�е�id
		String flag = (String) request.getSession().getAttribute("flag");//��ȡ���
		int count = 0;
//		System.out.println(flag);
		if(flag != null) {
			//�������޸ģ����������,�������ٱ�ǣ�����Ӱ���ж�
			request.getSession().removeAttribute("flag");
			try {
				count = specialCustomerService.updateToReserve(phone,name,customerType,address,id,manager);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(count > 0) {
				request.setAttribute("msg", "�޸Ŀͻ����ϳɹ���");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeleteSpecial").forward(request, response);
			}else {
				request.setAttribute("msg", "�޸Ŀͻ����ϳ������飡");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeleteSpecial").forward(request, response);
			}
		}else {
			//���������
			try {
				count = specialCustomerService.addToReserve(phone,name,customerType,address,manager);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(count > 0) {
				request.setAttribute("msg", "��ӿͻ����ϳɹ���");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeleteSpecial").forward(request, response);
			}else {
				request.setAttribute("msg", "��ӿͻ����ϳ������飡");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeleteSpecial").forward(request, response);
			}
		}
	
	}
	
	/**
	 * ���ר�Ϳͻ���Ϣ��������д����ҳ
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void addSpecial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String customerType = request.getParameter("type");
		//�ѿͻ����ͷ���session�����jspҳ��ȡ
		request.getSession().setAttribute("customerType", customerType);
		//��ȡ�����˵�Ҫ��ʾ����ҵ����
		List type = null;
		try {
			type = industryService.getIndustryType();
		} catch (SQLException e) {	
			e.printStackTrace();
		}
		if(type != null) {
			//����ҵ���ʹ���session��
			request.getSession().setAttribute("industryType", type);
			request.getRequestDispatcher("/WEB-INF/view/worker_customer_addSpecial.jsp").forward(request, response);
		}
		
		
	}
	
	
	/**
	 *  �޸Ļ�ɾ����Ϳͻ���Ϣ(ȥ�޸ĺ�ɾ��ҳ��)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateOrDeleteBig(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String search = request.getParameter("search");
		//kind�������������ģ����ѯ���޸�ɾ��ҳ��Ļ��ǲ�ѯҳ��ġ�
		String kind = request.getParameter("kind");
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
				pageBean = bigCustomerService.getTotalNotice(pageBean);
				//��ȡ��ҳ��ѯ����,���ѻ�ȡ�������ݼ��Ϸ���pageBean
				pageBean = bigCustomerService.query(pageBean);
				request.setAttribute("notices", pageBean);
			} catch (SQLException e) {
				e.printStackTrace();
			}
				request.setAttribute("bigCustomer", pageBean.getT());
				request.getRequestDispatcher("/WEB-INF/view/worker_customer_updateOrDeleteBig.jsp").forward(request, response);
			
		
//		else {
//			request.setAttribute("msg", "����ʧ�ܣ����ȵ�¼��");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}
		
	
		
	}
	
	/**
	 * �޸Ĵ�Ϳͻ���Ϣ
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
				request.getSession().setAttribute("flag", flag);//�޸ı��

			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(bigCustomer != null) {
				request.setAttribute("bigCustomer", bigCustomer);	
				//ת������ӵ�����ҳ�棨��Ӻ��޸�ͬһ��ҳ�棩
				request.getRequestDispatcher("workerCustomerServlet?method=addBig").forward(request, response);
			}else {
				request.setAttribute("msg", "�޸ĳ������飡");
				request.getRequestDispatcher("/worker_home.jsp").forward(request, response);
			
		}
//			else {
//			request.setAttribute("msg", "����ʧ�ܣ����ȵ�¼��");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}
	}
	
	/**
	 * ɾ����Ϳͻ���Ϣ
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
				request.setAttribute("msg", "ɾ���ͻ���Ϣ�ɹ���");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeleteBig").forward(request, response);
			}else {
				request.setAttribute("msg", "ɾ���������飡");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeleteBig").forward(request, response);
				}
		}else{
			request.setAttribute("msg", "ɾ��,��ȡid�������飡");
			request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeleteBig").forward(request, response);
		}
			
			
		
//			else {
//			request.setAttribute("msg", "����ʧ�ܣ����ȵ�¼��");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}
	}
	
	/**
	 * ��ѯ��Ϳͻ���Ϣ(����ģ����ѯ)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	private void queryBig(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String search = request.getParameter("search");
		//kind�������������ģ����ѯ���޸�ɾ��ҳ��Ļ��ǲ�ѯҳ��ġ�
		String kind = request.getParameter("kind");
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
				pageBean = bigCustomerService.getTotalNotice(pageBean);
				//��ȡ��ҳ��ѯ����,���ѻ�ȡ�������ݼ��Ϸ���pageBean
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
//			request.setAttribute("msg", "����ʧ�ܣ����ȵ�¼��");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}
		
		
		
	}
	
	/**
	 * ��Ӵ�Ϳͻ���Ϣ��ϣ��������ݵ����ݿ�
	 * @param request
	 * @param response
	 */
	private void addOrUpdateBigToReserve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String phone = request.getParameter("phone");
		String name = request.getParameter("name");
		String customerType = request.getParameter("customerType");
		String address = request.getParameter("address");
		String manager = request.getParameter("manager");
		String industry = request.getParameter("industry");//�������û�õ������ݿ���Ĭ��ֵ 
		String tc = request.getParameter("tc");
		String id = (String) request.getSession().getAttribute("id");//�޸�ѡ�е�id
		String flag = (String) request.getSession().getAttribute("flag");//��ȡ���
		int count = 0;
//		System.out.println(flag);
		if(flag != null) {
			//�������޸ģ����������,�������ٱ�ǣ�����Ӱ���ж�
			request.getSession().removeAttribute("flag");
			try {
				count = bigCustomerService.updateToReserve(phone,name,customerType,address,id,manager,industry,tc);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(count > 0) {
				request.setAttribute("msg", "�޸Ŀͻ����ϳɹ���");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeleteBig").forward(request, response);
			}else {
				request.setAttribute("msg", "�޸Ŀͻ����ϳ������飡");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeleteBig").forward(request, response);
			}
		}else {
			//���������
			try {
				count = bigCustomerService.addToReserve(phone,name,customerType,address,manager,industry,tc);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(count > 0) {
				request.setAttribute("msg", "��ӿͻ����ϳɹ���");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeleteBig").forward(request, response);
			}else {
				request.setAttribute("msg", "��ӿͻ����ϳ������飡");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeleteBig").forward(request, response);
			}
		}
	
	}
	
	/**
	 * ��Ӵ�Ϳͻ���Ϣ��������д����ҳ
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void addBig(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String customerType = request.getParameter("type");
		//�ѿͻ����ͷ���session�����jspҳ��ȡ
		request.getSession().setAttribute("customerType", customerType);
		//��ȡ�����˵�Ҫ��ʾ����ҵ����
		List type = null;
		try {
			type = industryService.getIndustryType();
		} catch (SQLException e) {	
			e.printStackTrace();
		}
		if(type != null) {
			//����ҵ���ʹ���session��
			request.getSession().setAttribute("industryType", type);
			request.getRequestDispatcher("/WEB-INF/view/worker_customer_addBig.jsp").forward(request, response);
		}
		
		
	}
	
	/**
	 *  �޸Ļ�ɾ���̿Ϳͻ���Ϣ(ȥ�޸ĺ�ɾ��ҳ��)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateOrDeletePublic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String search = request.getParameter("search");
		//kind�������������ģ����ѯ���޸�ɾ��ҳ��Ļ��ǲ�ѯҳ��ġ�
		String kind = request.getParameter("kind");
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
				pageBean = publicCustomerService.getTotalNotice(pageBean);
				//��ȡ��ҳ��ѯ����,���ѻ�ȡ�������ݼ��Ϸ���pageBean
				pageBean = publicCustomerService.query(pageBean);
				request.setAttribute("notices", pageBean);
			} catch (SQLException e) {
				e.printStackTrace();
			}
				request.setAttribute("publicCustomer", pageBean.getT());
				request.getRequestDispatcher("/WEB-INF/view/worker_customer_updateOrDeletePublic.jsp").forward(request, response);
			
		
//		else {
//			request.setAttribute("msg", "����ʧ�ܣ����ȵ�¼��");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}
		
	
		
	}
	
	/**
	 * �޸Ĺ��Ϳͻ���Ϣ
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
				request.getSession().setAttribute("flag", flag);//�޸ı��

			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(publicCustomer != null) {
				request.setAttribute("publicCustomer", publicCustomer);	
				//ת������ӵ�����ҳ�棨��Ӻ��޸�ͬһ��ҳ�棩
				request.getRequestDispatcher("workerCustomerServlet?method=addPublic").forward(request, response);
			}else {
				request.setAttribute("msg", "�޸ĳ������飡");
				request.getRequestDispatcher("/worker_home.jsp").forward(request, response);
			
		}
//			else {
//			request.setAttribute("msg", "����ʧ�ܣ����ȵ�¼��");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}
	}

	
	/**
	 * ɾ�����Ϳͻ���Ϣ
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
				request.setAttribute("msg", "ɾ���ͻ���Ϣ�ɹ���");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeletePublic").forward(request, response);
			}else {
				request.setAttribute("msg", "ɾ���������飡");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeletePublic").forward(request, response);
				}
		}else{
			request.setAttribute("msg", "ɾ��,��ȡid�������飡");
			request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeletePublic").forward(request, response);
		}
			
			
		
//			else {
//			request.setAttribute("msg", "����ʧ�ܣ����ȵ�¼��");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}
	}
	
	/**
	 * ��ѯ���Ϳͻ���Ϣ(����ģ����ѯ)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	private void queryPublic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String search = request.getParameter("search");
		//kind�������������ģ����ѯ���޸�ɾ��ҳ��Ļ��ǲ�ѯҳ��ġ�
		String kind = request.getParameter("kind");
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
				pageBean = publicCustomerService.getTotalNotice(pageBean);
				//��ȡ��ҳ��ѯ����,���ѻ�ȡ�������ݼ��Ϸ���pageBean
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
//			request.setAttribute("msg", "����ʧ�ܣ����ȵ�¼��");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}
		
		
		
	}
	
	/**
	 * ��ӹ��Ϳͻ���Ϣ��ϣ��������ݵ����ݿ�
	 * @param request
	 * @param response
	 */
	private void addOrUpdatePublicToReserve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String phone = request.getParameter("phone");
		String name = request.getParameter("name");
		String customerType = request.getParameter("customerType");
		String address = request.getParameter("address");

		String id = (String) request.getSession().getAttribute("id");//�޸�ѡ�е�id
		String flag = (String) request.getSession().getAttribute("flag");//��ȡ���
		int count = 0;
//		System.out.println(flag);
		if(flag != null) {
			//�������޸ģ����������,�������ٱ�ǣ�����Ӱ���ж�
			request.getSession().removeAttribute("flag");
			try {
				count = publicCustomerService.updateToReserve(phone,name,customerType,address,id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(count > 0) {
				request.setAttribute("msg", "�޸Ŀͻ����ϳɹ���");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeletePublic").forward(request, response);
			}else {
				request.setAttribute("msg", "�޸Ŀͻ����ϳ������飡");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeletePublic").forward(request, response);
			}
		}else {
			//���������
			try {
				count = publicCustomerService.addToReserve(phone,name,customerType,address);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(count > 0) {
				request.setAttribute("msg", "��ӿͻ����ϳɹ���");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeletePublic").forward(request, response);
			}else {
				request.setAttribute("msg", "��ӿͻ����ϳ������飡");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeletePublic").forward(request, response);
			}
		}
	
	}
	
	/**
	 * ��ӹ��Ϳͻ���Ϣ��������д����ҳ
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void addPublic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String customerType = request.getParameter("type");
		//�ѿͻ����ͷ���session�����jspҳ��ȡ
		request.getSession().setAttribute("customerType", customerType);
//		//��ȡ�����˵�Ҫ��ʾ����ҵ����
//		List type = null;
//		try {
//			type = industryService.getIndustryType();
//		} catch (SQLException e) {	
//			e.printStackTrace();
//		}
//		if(type != null) {
//			//����ҵ���ʹ���session��
//			request.getSession().setAttribute("industryType", type);
			request.getRequestDispatcher("/WEB-INF/view/worker_customer_addPublic.jsp").forward(request, response);
//		}
		
		
	}
	
	/**
	 * ��ѯ�ͻ���Ϣ(ȥѡ��ͻ�����ҳ��)
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
	 * �޸��̿Ϳͻ���Ϣ
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
					//�޸���ϣ���ȡ��ҵ���ͣ�����session�У���������һ���޸ı�ǣ����ֱ����޸Ļ������
					List type = industryService.getIndustryType();
					String flag = "update";
					request.getSession().setAttribute("flag", flag);//�޸ı��
					request.getSession().setAttribute("industryType", type);
				}
		
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(businessCustomer != null) {
				request.setAttribute("businessCustomer", businessCustomer);	
				//ת������ӵ�����ҳ�棨��Ӻ��޸�ͬһ��ҳ�棩
				request.getRequestDispatcher("workerCustomerServlet?method=addBusiness").forward(request, response);
			}else {
				request.setAttribute("msg", "�޸ĳ������飡");
				request.getRequestDispatcher("/worker_home.jsp").forward(request, response);
			
		}
//			else {
//			request.setAttribute("msg", "����ʧ�ܣ����ȵ�¼��");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}
	}

	/**
	 * ɾ���̿Ϳͻ���Ϣ
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
				request.setAttribute("msg", "ɾ���ͻ���Ϣ�ɹ���");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeleteBusiness").forward(request, response);
			}else {
				request.setAttribute("msg", "ɾ���������飡");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeleteBusiness").forward(request, response);
				}
		}else{
			request.setAttribute("msg", "ɾ��,��ȡid�������飡");
			request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeleteBusiness").forward(request, response);
		}
			
			
		
//			else {
//			request.setAttribute("msg", "����ʧ�ܣ����ȵ�¼��");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}
	}
		/**
		 * �޸ĺ�ɾ��(ȥѡ��ͻ�����ҳ��)
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
	 *  �޸Ļ�ɾ���̿Ϳͻ���Ϣ(ȥ�޸ĺ�ɾ��ҳ��)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateOrDeleteBusiness(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String search = request.getParameter("search");
		//kind�������������ģ����ѯ���޸�ɾ��ҳ��Ļ��ǲ�ѯҳ��ġ�
		String kind = request.getParameter("kind");
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
				pageBean = businessCustomerService.getTotalNotice(pageBean);
				//��ȡ��ҳ��ѯ����,���ѻ�ȡ�������ݼ��Ϸ���pageBean
				pageBean = businessCustomerService.query(pageBean);
				request.setAttribute("notices", pageBean);
			} catch (SQLException e) {
				e.printStackTrace();
			}
				request.setAttribute("businessCustomer", pageBean.getT());
				request.getRequestDispatcher("/WEB-INF/view/worker_customer_updateOrDeleteBusiness.jsp").forward(request, response);
			
		
//		else {
//			request.setAttribute("msg", "����ʧ�ܣ����ȵ�¼��");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}
		
	
		
	}

//	/**
//	 *  ��ѯ�ͻ���Ϣ(ģ����ѯ) ����ϲ����˲�ѯ���棬�����������
//	 * @param request
//	 * @param response
//	 * @throws ServletException
//	 * @throws IOException
//	 */
//	private void queryBusinessByPart(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
//		String content = request.getParameter("content");
//		//kind�������������ģ����ѯ���޸�ɾ��ҳ��Ļ��ǲ�ѯҳ��ġ�
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
//				request.setAttribute("msg", "��ѯ�������飡");
//				request.getRequestDispatcher("/WEB-INF/view/worker_customer_query.jsp").forward(request, response);
//				}
////			else {
////			request.setAttribute("msg", "����ʧ�ܣ����ȵ�¼��");
////			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
////		}
//	}

	/**
	 * ��ѯ�̿Ϳͻ���Ϣ(����ģ����ѯ)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	private void queryBusiness(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String search = request.getParameter("search");
		//kind�������������ģ����ѯ���޸�ɾ��ҳ��Ļ��ǲ�ѯҳ��ġ�
		String kind = request.getParameter("kind");
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
				pageBean = businessCustomerService.getTotalNotice(pageBean);
				//��ȡ��ҳ��ѯ����,���ѻ�ȡ�������ݼ��Ϸ���pageBean
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
//			request.setAttribute("msg", "����ʧ�ܣ����ȵ�¼��");
//			request.getRequestDispatcher("/workerlogin.jsp").forward(request, response);
//		}
		
		
		
	}

	/**
	 * ����̿Ϳͻ���Ϣ��ϣ��������ݵ����ݿ�
	 * @param request
	 * @param response
	 */
	private void addOrUpdateBusinessToReserve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String phone = request.getParameter("phone");
		String name = request.getParameter("name");
		String customerType = request.getParameter("customerType");
		String address = request.getParameter("address");
		String manager = request.getParameter("manager");
		String industry = request.getParameter("industry");//�������û�õ������ݿ���Ĭ��ֵ 
		String id = (String) request.getSession().getAttribute("id");
		String flag = (String) request.getSession().getAttribute("flag");//��ȡ���
		int count = 0;
//		System.out.println(flag);
		if(flag != null) {
			//�������޸ģ����������,�������ٱ�ǣ�����Ӱ���ж�
			request.getSession().removeAttribute("flag");
			try {
				count = businessCustomerService.updateToReserve(phone,name,customerType,address,manager,industry,id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(count > 0) {
				request.setAttribute("msg", "�޸Ŀͻ����ϳɹ���");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeleteBusiness").forward(request, response);
			}else {
				request.setAttribute("msg", "�޸Ŀͻ����ϳ������飡");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeleteBusiness").forward(request, response);
			}
		}else {
			//���������
			try {
				count = businessCustomerService.addToReserve(phone,name,customerType,address,manager,industry);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(count > 0) {
				request.setAttribute("msg", "��ӿͻ����ϳɹ���");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeleteBusiness").forward(request, response);
			}else {
				request.setAttribute("msg", "��ӿͻ����ϳ������飡");
				request.getRequestDispatcher("workerCustomerServlet?method=updateOrDeleteBusiness").forward(request, response);
			}
		}
	
	}

	/**
	 * ����̿Ϳͻ���Ϣ��������д����ҳ
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void addBusiness(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String customerType = request.getParameter("type");
		//�ѿͻ����ͷ���session�����jspҳ��ȡ
		request.getSession().setAttribute("customerType", customerType);
		//��ȡ�����˵�Ҫ��ʾ����ҵ����
		List type = null;
		try {
			type = industryService.getIndustryType();
		} catch (SQLException e) {	
			e.printStackTrace();
		}
		if(type != null) {
			//����ҵ���ʹ���session��
			request.getSession().setAttribute("industryType", type);
			request.getRequestDispatcher("/WEB-INF/view/worker_customer_addBusiness.jsp").forward(request, response);
		}
		
		
	}

	/**
	 * ��ȡ�ͻ�����,��ת�����ҳ��ĵ�һҳ
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
