package com.ns.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.ns.common.MyException;
import com.ns.common.MyFactory;
import com.ns.iservice.INsHumanService;
import com.ns.pojo.NsAdmin;
import com.ns.pojo.NsHuman;
import com.ns.pojo.NsUser;
import com.ns.tag.NsUserCheckTag;

public class NsHumanAction extends NsBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private INsHumanService humanService = (INsHumanService)MyFactory.getBean("humanService");
	
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
			System.out.println("NsHumanAction:"+method);
			if(method.equals("manage")){
				manage(request,response);		//�������showall��������У�Ȼ��Ž�PageList
			}else if(method.equals("browse")){
				browse(request,response);		//Ԥ��������show��֮������תҳ��
			}else if(method.equals("show")){
				show(request,response);			//��ʾ,���ں����ݿ⽻������ѯ
			}else if(method.equals("showall")){
				showall(request,response);		//����
			}else if(method.equals("add")){
				add(request,response);			//���
			}else if(method.equals("willEdit")){
				willEdit(request,response);		//Ҫ�޸�
			}else if(method.equals("edit")){
				edit(request,response);			//�޸�
			}else if(method.equals("delete")){
				delete(request,response);		//ɾ��
			}else if(method.equals("shownum")){
				shownum(request,response);		//չʾƱ��
			}else if(method.equals("addNum")){
				addNum(request,response);		//ͶƱ
			}
		}
	}

	
	@Override
	protected void manage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		showall(request, response);
		RequestDispatcher rd = request.getRequestDispatcher("/human/human_manage.jsp");
		rd.forward(request, response);
	}

	

	@Override
	protected void browse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		showall(request, response);
		RequestDispatcher rd = request.getRequestDispatcher("/human/human_select.jsp");
		rd.forward(request, response);
	}

	@Override
	protected void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			NsHuman human = humanService.findHumanById(Integer.parseInt(request.getParameter("humanId")));
			request.setAttribute("human", human);
			RequestDispatcher rd = request.getRequestDispatcher("/human/show.jsp");
			rd.forward(request, response);
		} catch (MyException e) {
			// TODO: handle exception
			request.setAttribute("msg", e.getMessage()+"<a href=\"JavaScript:window.history.back()\">����</a>");
			RequestDispatcher rd = request.getRequestDispatcher("/common/message.jsp");
			rd.forward(request, response);
		}
	}

	@Override
	protected void showall(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			List<NsHuman> humanlist = humanService.findHumanAll();
			request.setAttribute("humanlist", humanlist);
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
		NsHuman human = new NsHuman();
		human.setHuman_name(request.getParameter("humanName"));
		human.setHuman_address(request.getParameter("humanAddress"));
		int humanAge = Integer.parseInt(request.getParameter("humanAge"));
		human.setHuman_age(humanAge);
		human.setHuman_work(request.getParameter("humanWork"));
		human.setHuman_intr(request.getParameter("humanIntr"));
		int humanNum = Integer.parseInt(request.getParameter("humanNum"));
		human.setHuman_num(humanNum);
		try {
			humanService.addHuman(human);
			manage(request, response);
		} catch (Exception e) {
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
			int humanId = Integer.parseInt(request.getParameter("humanId"));
			NsHuman human = humanService.findHumanById(humanId);
			request.setAttribute("human", human);
			RequestDispatcher rd = request.getRequestDispatcher("/human/edit.jsp");
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
		NsHuman human = new NsHuman();
		int humanId = Integer.parseInt(request.getParameter("humanId"));
		human.setHuman_id(humanId);
		human.setHuman_name(request.getParameter("humanName"));
		human.setHuman_address(request.getParameter("humanAddress"));
		int humanAge = Integer.parseInt(request.getParameter("humanAge"));
		human.setHuman_age(humanAge);
		human.setHuman_work(request.getParameter("humanWork"));
		human.setHuman_intr(request.getParameter("humanIntr"));
		int humanNum = Integer.parseInt(request.getParameter("humanNum"));
		human.setHuman_num(humanNum);
		try {
			humanService.editHuman(human);
			manage(request, response);
		} catch (Exception e) {
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
		int id = Integer.parseInt(request.getParameter("humanId"));
		try {
			humanService.deleteHuman(id);
			manage(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", e.getMessage()+"<a href=\"JavaScript:window.history.back()\">����</a>");
			RequestDispatcher rd = request.getRequestDispatcher("/common/message.jsp");
			rd.forward(request, response);
		}
	}
	
	protected void shownum(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		showall(request, response);
		RequestDispatcher rd = request.getRequestDispatcher("/human/human_number.jsp");
		rd.forward(request, response);
	}
	
	protected void addNum(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		NsAdmin admin = (NsAdmin) session.getAttribute("admin");
		NsUser user = (NsUser) session.getAttribute("user");
		if(admin!=null||user!=null){
			Integer num = (Integer)session.getAttribute("num");
			try {
				if(num==0){
					Integer id = Integer.parseInt(request.getParameter("humanId"));
						humanService.updateNum(id);
						session.setAttribute("num", 1);
						response.getWriter().print("ͶƱ�ɹ��������ظ�ͶƱ��");
				}else{
					RequestDispatcher rd = request.getRequestDispatcher("/human/numerror.jsp");
					rd.forward(request, response);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}else{
			//��ͻ������
			response.getWriter().print("��û�е�¼����Ȩ�޲������㲻��ͶƱ��");
		}  
	}
}
