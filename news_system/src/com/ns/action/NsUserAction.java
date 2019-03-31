package com.ns.action;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.ns.common.MyException;
import com.ns.common.MyFactory;
import com.ns.iservice.INsAdminService;
import com.ns.iservice.INsUserService;
import com.ns.pojo.NsAdmin;
import com.ns.pojo.NsUser;

public class NsUserAction extends NsBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private INsUserService userService = (INsUserService)MyFactory.getBean("userService");
	private INsAdminService adminService = (INsAdminService)MyFactory.getBean("adminService");
	@Override
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(isMultipart){
			upfile(request,response);		//�ϴ��ļ�
		}else{
			String method = request.getParameter("method");
			if(method.equals("manage")){
				manage(request,response);		//����
			}else if(method.equals("browse")){
				browse(request,response);		//Ԥ��
			}else if(method.equals("show")){
				show(request,response);			//��ʾ
			}else if(method.equals("showall")){
				showall(request,response);		//��ʾ����
			}else if(method.equals("add")){
				add(request,response);			//���
			}else if(method.equals("willEdit")){
				willEdit(request,response);		//Ҫ�޸�
			}else if(method.equals("edit")){
				edit(request,response);			//�޸�
			}else if(method.equals("delete")){
				delete(request,response);		//ɾ��
			}else if(method.equals("login")){
				login(request,response);		//��¼
			}
		}
	}

	@Override
	protected void upfile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	@Override
	protected void manage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		showall(request, response);
		RequestDispatcher rd = request.getRequestDispatcher("/user/manage.jsp");
		rd.forward(request, response);
	}

	@Override
	protected void browse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	@Override
	protected void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			System.out.println("UserAction+show");
			NsUser user = userService.findUserById(Integer.parseInt(request.getParameter("userId")));
			request.setAttribute("user", user);
			RequestDispatcher rd = request.getRequestDispatcher("/user/show.jsp");
			rd.forward(request, response);
		} catch (MyException e) {
			// TODO: handle exception
			request.setAttribute("msg", e.getMessage()+"<a href=\"JavaScript:window.history.back()\">����</a>");
			RequestDispatcher rd = request.getRequestDispatcher("/common/message.jsp");
			rd.forward(request, response);
		}
	}

	protected void showall(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			List<NsUser> userlist = userService.findUsersAll();
			request.setAttribute("userlist", userlist);
		} catch (MyException e) {
			// TODO: handle exception
			request.setAttribute("msg", e.getMessage()+"<a href=\"JavaScript:window.history.back()\">����</a>");
			RequestDispatcher rd = request.getRequestDispatcher("/common/message.jsp");
			rd.forward(request, response);
		}
	}
	
	@Override
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		NsUser user = new NsUser();
		user.setUser_name(request.getParameter("userName"));
		user.setUser_rname(request.getParameter("userRname"));
		user.setUser_password(request.getParameter("userPassword"));
		user.setUser_email(request.getParameter("userEmail"));
		user.setUser_tel(request.getParameter("userTel"));
		Date date = new Date(System.currentTimeMillis());
		user.setUser_datetime(date);
		int userRight = 2;
		user.setUser_right(userRight);
		try {
			userService.addUser(user);
			manage(request, response);
		} catch (MyException e) {
			// TODO: handle exception
			request.setAttribute("msg", e.getMessage()+"<a href=\"JavaScript:window.history.back()\">����</a>");
			RequestDispatcher rd = request.getRequestDispatcher("/common/message.jsp");
			rd.forward(request, response);
		}
	}

	@Override
	protected void willEdit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Integer userId = Integer.parseInt(request.getParameter("userId"));
			NsUser user =userService.findUserById(userId);
			request.setAttribute("user", user);
			RequestDispatcher rd = request.getRequestDispatcher("/user/edit.jsp");
			rd.forward(request, response);
		} catch (MyException e) {
			// TODO: handle exception
			request.setAttribute("msg", e.getMessage()+"<a href=\"JavaScript:window.history.back()\">����</a>");
			RequestDispatcher rd = request.getRequestDispatcher("/common/message.jsp");
			rd.forward(request, response);
		}
	}

	@Override
	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		NsUser user = new NsUser();
		user.setUser_id(Integer.parseInt(request.getParameter("userId")));
		user.setUser_name(request.getParameter("userName"));
		user.setUser_rname(request.getParameter("userRname"));
		user.setUser_password(request.getParameter("userPassword"));
		user.setUser_email(request.getParameter("userEmail"));
		user.setUser_tel(request.getParameter("userTel"));
		Date date = new Date(System.currentTimeMillis());
		user.setUser_datetime(date);
		int userRight = 2;
		user.setUser_right(userRight);
		try {
			userService.editUser(user);
			manage(request, response);
		} catch (MyException e) {
			// TODO: handle exception
			request.setAttribute("msg", e.getMessage()+"<a href=\"JavaScript:window.history.back()\">����</a>");
			RequestDispatcher rd = request.getRequestDispatcher("/common/message.jsp");
			rd.forward(request, response);
		}
	}  

	@Override
	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer id = Integer.parseInt(request.getParameter("userId"));
		try{
			userService.deleteUser(id);
			manage(request, response);
		}catch(MyException e){
			request.setAttribute("msg", e.getMessage()+"<a href=\"JavaScript:window.history.back()\">����</a>");
			RequestDispatcher rd = request.getRequestDispatcher("/common/message.jsp");
			rd.forward(request, response);
		}
	}
	
	protected void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName =request.getParameter("userName");
		String userPwd = request.getParameter("userPwd");
		String url = null;
		try {
			NsUser user = userService.findUserByNameAndPwd(userName, userPwd);
			HttpSession session = request.getSession();
			if(user != null){
				System.out.println("�û���¼�ɹ�");
				session.setAttribute("user", user);
				url = "index";
			}else{
				System.out.println("�û���¼ʧ��");
				NsAdmin admin =	adminService.findAdminByNameAndPwd(userName, userPwd);
				session.setAttribute("admin", admin);
				url = "index";
				if(admin == null){
					System.out.println("����Ա��¼ʧ��");
					request.getSession().setAttribute("mrgss", "�����������������������");
					url = "user/login.jsp";
				}
				System.out.println("����Ա��¼�ɹ�");
			}
			response.sendRedirect("/news_system/"+url);
		} catch (MyException e) {
			// TODO: handle exception
			request.setAttribute("msg", e.getMessage()+"<a href=\"JavaScript:window.history.back()\">����</a>");
			RequestDispatcher rd = request.getRequestDispatcher("/common/message.jsp");
			rd.forward(request, response);
		}
	}
	
}
